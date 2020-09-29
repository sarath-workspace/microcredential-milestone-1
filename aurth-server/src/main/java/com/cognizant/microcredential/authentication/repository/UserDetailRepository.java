package com.cognizant.microcredential.authentication.repository;

import com.cognizant.microcredential.authentication.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDetailRepository extends CrudRepository<User,Integer> {
    Optional<User> findByUsername(String name);
}