package org.example.repository;

import org.example.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(value = "User.roles", type = EntityGraph.EntityGraphType.LOAD)
    Optional<User> findByEmail(String username);

    Optional<User> findByUserName(String userName);

    @EntityGraph(value = "User.search", type = EntityGraph.EntityGraphType.FETCH)
    List<User> searchUsers(String searchTerm, Pageable pageable);

    @EntityGraph(value = "User.search", type = EntityGraph.EntityGraphType.FETCH)
    List<User> searchUsers123(String searchTerm, Pageable pageable);


}
