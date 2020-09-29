package com.cognizant.microcredential.userservice.repository;

import com.cognizant.microcredential.userservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {

}
