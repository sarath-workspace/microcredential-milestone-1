package com.cognizant.microcredential.userservice.service;

import com.cognizant.microcredential.userservice.model.User;

public interface UserService {

    public abstract User addUser(User product);

    public abstract User updateUser(Long id, User product);

    public abstract User viewUser(long userId);

}
