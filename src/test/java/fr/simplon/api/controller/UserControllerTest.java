package fr.simplon.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.simplon.api.model.User;
import fr.simplon.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Sauron");
        user.setId(705);
        user.setEmail("jeTeVois@monOeil.com");
        user.setPassword("killHobbits");
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("Sauron"));
    }

    @Test
    void testGetUserById() throws Exception {
        when(userRepository.findById(705)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/705"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Sauron"));
    }

    @Test
    void testCreateUser() throws Exception {
        User newUser = new User("Sam");
        when(userRepository.save(newUser)).thenReturn(newUser);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Sam"));
    }
}
