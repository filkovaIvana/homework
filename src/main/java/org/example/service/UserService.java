package org.example.service;

import org.example.entity.User;
import org.example.entity.UserRight;
import org.example.entity.UserRole;
import org.example.exception.ValidationException;
import org.example.model.UserCreate;
import org.example.model.UserReturn;
import org.example.repository.AreaRepository;
import org.example.repository.UserRepository;
import org.example.repository.UserRightRepository;
import org.example.repository.UserRoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private UserRepository userRepository;
    private AreaRepository areaRepository;
    private PasswordEncoder passwordEncoder;

    private UserRightRepository userRightRepository;

    private UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository, AreaRepository areaRepository, UserRoleRepository userRoleRepository, UserRightRepository userRightRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.areaRepository = areaRepository;
        this.userRoleRepository = userRoleRepository;
        this.userRightRepository = userRightRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(NoSuchElementException::new);
    }

    public Optional<User> getUserByUserId(Long userId) {
        return userRepository.findById(userId);
    }

    public User create(UserCreate model){
//        if(userRepository.findByEmail(model.getEmail()).isPresent()){
//             throw new DuplicateEmailFoundException();
//        }
        System.out.println("ENTERED CRETE SERVICE! ");
        User user = new User();
        user.setActive(true);
        System.out.println("FIRST NAME: " + model.getFirstName());
        if(model.getFirstName()==null || model.getLastName()==null || model.getEmail()==null || model.getPassword()==null )
        {    System.out.println("SHOULD THROW!!!");
             throw new ValidationException();
        }
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setUserName(model.getUserName());
        user.setEmail(model.getEmail());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setPhoneNumber(model.getPhoneNumber());
        UserRole userRole = new UserRole("ROLE_ADMIN");
        userRole.setDescription("This is admin role");
        System.out.println("This is admin role");
//        UserRight userRight1 = new UserRight("ADMIN_RIGHT");
//        userRightRepository.save(userRight1);
        UserRight userRight2 = new UserRight("ADD");
        userRightRepository.save(userRight2);
        UserRight userRight3 = new UserRight("EDIT");
        userRightRepository.save(userRight3);
        UserRight userRight4 = new UserRight("REMOVE");
        userRightRepository.save(userRight4);
//
        userRole.setRights(Arrays.asList(
                userRight2, userRight3, userRight4
        ));
//        UserRole userRole = new UserRole("ROLE_STANDARD");
//        userRole.setDescription("User role");
//        userRole.setRights(Arrays.asList(new UserRight("LIST"),
//                new UserRight("ADD"), new UserRight("EDIT"),
//                new UserRight("DELETE")
//        ));



        userRoleRepository.findByName("ROLE_ADMIN").ifPresentOrElse(value -> {
            System.out.println("Found: " + value);
            user.addRole(value);
                },
                () -> {System.out.println("Not found");
            userRoleRepository.save(userRole);
            user.addRole(userRole); }
        );
//
//        (defaultRole -> {
//        userRoleRepository.save(userRole);
//        user.addRole(userRole);
//        });

        userRepository.save(user);
        return user;
    }

    public User edit(User user, UserReturn model) {
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());

        if(model.getPassword().length() > 0) {
            user.setPassword(passwordEncoder.encode(model.getPassword()));
        }
        user.setPhoneNumber(model.getPhoneNumber());

        userRepository.save(user);
        return user;
    }

    public List<User> getAllUser() {
        List<User> result = new ArrayList<User>();
        userRepository.findAll().forEach(result::add);
        return result;
    }

    public User addImageToUser(Long userId, String imageUrl){
        User user = userRepository.findById(userId).get();
        user.setImageURl(imageUrl);
        userRepository.save(user);
        return user;
    }

}


