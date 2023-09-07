package org.example.security;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

public class HMUAuthenticationManager implements AuthenticationManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public HMUAuthenticationManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*  this method is automatically called on login request after all automatically called methods from JWTAuthenticationFilter
     *  for obtaining UserCredentials are done (except successfulAuthentication()),
     *  and checks if the username and password are valid, and if so on finishing it will proceed to
     *  JWTAuthenticationFilter -> successfulAuthentication() method that generates the token
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        if( !authentication.getClass().isAssignableFrom(UsernamePasswordAuthenticationToken.class) || authentication.isAuthenticated() ){
            throw new AuthenticationServiceException("Got wrong type of auth token");
        }

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;

        String username = String.valueOf(authToken.getPrincipal());
        if( username == null || username.trim().isEmpty() ){

            throw new BadCredentialsException("No valid username got provided");
        }

        User storedUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("Username not found"));

        String storedCryptedPassword = Optional.ofNullable(storedUser.getPassword())
                .orElseThrow(() -> new AccountExpiredException("No valid password found"));

        String requestedPassword = String.valueOf(authToken.getCredentials());

        if( !passwordEncoder.matches(requestedPassword, storedCryptedPassword) ){
            throw new BadCredentialsException("Provided password did not match stored password");
        }

        if( !storedUser.isActive() ){
            throw new BadCredentialsException("User was not active");
        }

        // return new token including the rights of the user, but do not store password in it
        return new UsernamePasswordAuthenticationToken(storedUser.getEmail(), null, getAuthorities(storedUser));
    }

    /*
     * This returns a list with all rights of the user, which are gathered from assigned roles.
     */
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        if( user == null || user.isActive() ){
            return Collections.emptyList();
        }
        return user.getRoles()
                .stream()
                .map(userRoleAssociation -> userRoleAssociation.getRights())
                .flatMap(Collection::stream)
                .map(userRight -> new SimpleGrantedAuthority(userRight.getName()))
                .collect(Collectors.toList());
    }

}
