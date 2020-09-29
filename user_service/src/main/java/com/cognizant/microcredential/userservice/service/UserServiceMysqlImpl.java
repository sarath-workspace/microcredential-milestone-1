package com.cognizant.microcredential.userservice.service;

import com.cognizant.microcredential.userservice.controller.UserController;
import com.cognizant.microcredential.userservice.exception.UserNotFound;
import com.cognizant.microcredential.userservice.model.User;
import com.cognizant.microcredential.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mysql")
public class UserServiceMysqlImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {

        logger.info("update service in user service was called");
        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new UserNotFound(id));

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setDateOfBirth(user.getDateOfBirth());
        userToUpdate.setEmail(user.getEmail());

        logger.info("the user with id {} is updated with values {}", id, userToUpdate);

        return userRepository.save(userToUpdate);
    }

    @Override
    public User viewUser(long userId) {
        logger.info("retrieving user details with userid {}", userId);
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
    }
}
