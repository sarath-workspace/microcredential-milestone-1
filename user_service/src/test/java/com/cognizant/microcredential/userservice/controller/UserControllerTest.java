package com.cognizant.microcredential.userservice.controller;

import com.cognizant.microcredential.userservice.common.TestUtil;
import com.cognizant.microcredential.userservice.model.User;
import com.cognizant.microcredential.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void addProduct() throws Exception {

        User userToAdd = new User();

        userToAdd.setFirstName("firstname");
        userToAdd.setLastName("lastname");
        userToAdd.setEmail("firstname@email.com");
        userToAdd.setDateOfBirth(Date.valueOf("2020-08-05"));

        User userAdded = new User();

        userAdded.setId(1);
        userAdded.setFirstName("firstname");
        userAdded.setLastName("lastname");
        userAdded.setEmail("firstname@email.com");
        userAdded.setDateOfBirth(Date.valueOf("2020-08-05"));

        when(userService.addUser(any(User.class))).thenReturn(userAdded);

        mvc.perform(post("/api/user/add")
                .content(TestUtil.convertObjectToJsonBytes(userToAdd))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(userToAdd.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(userToAdd.getLastName())))
                .andExpect(jsonPath("$.email", is(userToAdd.getEmail())));
    }

    @Test
    void updateProduct() throws Exception {
        User userToUpdate = new User();

        userToUpdate.setFirstName("firstname");
        userToUpdate.setLastName("lastname");
        userToUpdate.setEmail("firstname@email.com");
        userToUpdate.setDateOfBirth(Date.valueOf("2020-08-05"));

        User updatedUser = new User();

        updatedUser.setId(1L);
        updatedUser.setFirstName("firstname");
        updatedUser.setLastName("lastname");
        updatedUser.setEmail("firstname@email.com");
        updatedUser.setDateOfBirth(Date.valueOf("2020-08-05"));

        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updatedUser);

        mvc.perform(put("/api/user/update/1")
                .content(TestUtil.convertObjectToJsonBytes(userToUpdate))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(userToUpdate.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(userToUpdate.getLastName())))
                .andExpect(jsonPath("$.email", is(userToUpdate.getEmail())));
    }

    @Test
    void getUser() throws Exception {
        User userDetails = new User();

        userDetails.setId(1L);
        userDetails.setFirstName("firstname");
        userDetails.setLastName("lastname");
        userDetails.setEmail("firstname@email.com");
        userDetails.setDateOfBirth(Date.valueOf("2020-08-05"));

        when(userService.viewUser(1L)).thenReturn(userDetails);

        mvc.perform(get("/api/user/view/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(userDetails.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(userDetails.getLastName())))
                .andExpect(jsonPath("$.email", is(userDetails.getEmail())));

    }
}