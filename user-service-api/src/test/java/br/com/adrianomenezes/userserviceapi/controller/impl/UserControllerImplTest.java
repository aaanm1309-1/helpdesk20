package br.com.adrianomenezes.userserviceapi.controller.impl;

import br.com.adrianomenezes.userserviceapi.entity.User;
import br.com.adrianomenezes.userserviceapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.adrianomenezes.userserviceapi.creator.CreatorUtils.generateMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByIdWithSuccess() throws Exception {
        final var entity = generateMock(User.class);
        final var userId = userRepository.save(entity).getId();

        mockMvc.perform(get("/api/v1/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value(entity.getName()))
                .andExpect(jsonPath("$.email").value(entity.getEmail()))
                .andExpect(jsonPath("$.password").value(entity.getPassword()))
                .andExpect(jsonPath("$.profiles").isArray())
                ;

        userRepository.deleteById(userId);
    }

    @Test
    void testFindByIdWithNotFoundException() throws Exception {
        final var userId = "123456";

        mockMvc.perform(get("/api/v1/users/{id}", userId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found. Id: "+ userId+", Type: UserResponse"))
                .andExpect(jsonPath("$.error").value(NOT_FOUND.getReasonPhrase()))
                .andExpect(jsonPath("$.path").value("/api/v1/users/"+ userId))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
        ;

    }

}