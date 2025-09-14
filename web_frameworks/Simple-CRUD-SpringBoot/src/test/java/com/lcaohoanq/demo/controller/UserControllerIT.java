package com.lcaohoanq.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lcaohoanq.demo.IntegrationTest;
import com.lcaohoanq.demo.domain.user.User;
import com.lcaohoanq.demo.domain.user.UserDTO;
import com.lcaohoanq.demo.domain.user.UserRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@IntegrationTest
@AutoConfigureMockMvc
@Transactional //rollback after each test
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        //clean up db before each test
        userRepository.deleteAll();
    }


    @Test
    public void createUser_shouldReturnUser_whenValid() throws Exception {
        //arrange
        var inputUser = new UserDTO("testuser", "test@example.com", "password123");
        //action
       String result =  mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(inputUser)))
                            .andExpect(status()
                                           .isCreated())
                            .andReturn().getResponse().getContentAsString();

       //assert
       var outputUser = objectMapper.readValue(result, User.class);
       assertEquals(inputUser.login(), outputUser.getLogin());
    }

    @Test
    public void createUser_shouldReturnBadRequest_whenInvalidInput() throws Exception {
        // arrange: login quá ngắn, password thiếu, email trống
        var invalidUser = new UserDTO("", "abc", "123");

        // action + assert
        mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(invalidUser)))
            .andExpect(status().isBadRequest()) // ❌ expect 400
            .andReturn();
    }

    @Test
    public void getAllUsers_shouldReturnListOfUsers() throws Exception {
        // arrange

        //this.userRepository.deleteAll();

        var user1 = new User(null, "test", "test@gmail.com" , "login", "test");
        var user2 = new User(null, "test", "hoang@gmail.com" , "test", "test");

        var data = List.of(user1, user2);
        userRepository.saveAll(data);

        // act
        String resultStr = mockMvc.perform(get("/api/users")
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        var result = objectMapper.readValue(resultStr, new TypeReference<List<User>>() {});

        // assert
        assertEquals(2, result.size());
        assertEquals("login", result.get(0).getLogin());
        assertEquals("hoang@gmail.com", result.get(1).getEmail());

    }

    @Test
    public void getUserById_shouldReturnUser_whenUserExists() throws Exception {
        // arrange
        var user = new User(null, "test", "test@gmail.com", "login", "test");
        var savedUser = userRepository.saveAndFlush(user);

        // act
        String resultStr = mockMvc.perform(get("/api/users/{id}", savedUser.getId())
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        var result = objectMapper.readValue(resultStr, User.class);

        // assert
        assertEquals(savedUser.getId(), result.getId());

    }

    @Test
    public void getUserById_shouldReturnNotFound_whenUserNotExists() throws Exception {
        // arrange
        Long nonExistentId = 999L;

        // act + assert
        mockMvc.perform(get("/api/users/{id}", nonExistentId)
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

    }

    @Test
    public void deleteUser_shouldReturnNoContent_whenUserExists() throws Exception {
        // arrange
        var user = new User(null, "test", "test@gmail.com", "login", "test");
        var savedUser = userRepository.save(user);

        // act + assert
        mockMvc.perform(delete("/api/users/{id}", savedUser.getId())
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // verify user is deleted
        assertEquals(0, userRepository.count());
    }

    @Test
    public void deleteUser_shouldReturn404_whenUserNotExists() throws Exception {
        Long nonExistentId = 999L;

        mockMvc.perform(delete("/api/users/{id}", nonExistentId)
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(result ->
                           assertInstanceOf(NoSuchElementException.class,
                                            result.getResolvedException()))
            .andExpect(result ->
                           assertEquals("User not found with id: 999",
                                        Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }




}
