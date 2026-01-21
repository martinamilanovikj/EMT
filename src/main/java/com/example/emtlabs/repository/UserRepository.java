package com.example.emtlabs.repository;

import com.example.emtlabs.model.domain.User;
import com.example.emtlabs.model.enumerations.Role;
import com.example.emtlabs.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    @EntityGraph(value = "User.withoutWishlists", type = EntityGraph.EntityGraphType.LOAD)
    List<User> findAll();

    @Query("SELECT u.username AS username, u.name AS name, u.surname AS surname FROM User u WHERE u.role = :role")
    List<UserProjection> findAllByRole(@Param("role") Role role);

    @Query("SELECT u FROM User u")
    List<UserProjection> findAllProjected();

}



