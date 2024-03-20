package br.com.adrianomenezes.userserviceapi.controller.impl;

import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.userserviceapi.entity.User;
import br.com.adrianomenezes.userserviceapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static br.com.adrianomenezes.userserviceapi.creator.CreatorUtils.generateMock;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerImplTest {

    public static final String BASE_URI = "/api/v1/users";
    public static final String EMAIL_VALID = "hjfksadhdfsaj@mail.com";
    public static final String EMAIL_NOT_VALID = "hjfksadhdfsajmail.com";
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


    @Test
    void testFindAllWithSuccess() throws Exception {
        final var entity1 = generateMock(User.class).withName("teste1");
        final var entity2 = generateMock(User.class);

        userRepository.saveAll(List.of(entity1,entity2));

        mockMvc.perform(get(BASE_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").isNotEmpty())
                .andExpect(jsonPath("$[1]").isNotEmpty())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].profiles").isArray())
                .andExpect(jsonPath("$[1].id").isNotEmpty())
                .andExpect(jsonPath("$[1].profiles").isArray())
                .andExpect(jsonPath("$[?(@.name=='"+ entity1.getName() +"')]").exists())
                .andExpect(jsonPath("$[?(@.name=='"+ entity2.getName() +"')]").exists())
                ;

        userRepository.deleteAll(List.of(entity1,entity2));

    }


    @Test
    void testSaveWithSuccess() throws Exception {
        final var request = generateMock(CreateUserRequest.class).withEmail(EMAIL_VALID);


        mockMvc.perform(post(BASE_URI)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(toJson(request))
                )
                .andExpect(status().isCreated())
        ;

        userRepository.deleteByEmail(EMAIL_VALID);
    }


    @Test
    void testSaveWithConflict() throws Exception {

        final var entity = generateMock(User.class).withEmail(EMAIL_VALID);
        final var request = generateMock(CreateUserRequest.class).withEmail(EMAIL_VALID);
        userRepository.save(entity);


        mockMvc.perform(post(BASE_URI)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(toJson(request))
                )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Email [ "+ EMAIL_VALID + " ] already exists."))
                .andExpect(jsonPath("$.error").value("Conflict"))
                .andExpect(jsonPath("$.path").value(BASE_URI))
                .andExpect(jsonPath("$.status").value(CONFLICT.value()))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
        ;

        userRepository.deleteByEmail(EMAIL_VALID);

    }

    @Test
    void testSaveWithBadRequest() throws Exception {
        final var entity = generateMock(CreateUserRequest.class).withEmail(EMAIL_NOT_VALID);


        mockMvc.perform(post(BASE_URI)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(toJson(entity))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Exception in validation attributes"))
                .andExpect(jsonPath("$.error").value("Validation Exception"))
                .andExpect(jsonPath("$.path").value(BASE_URI))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.errors[?(@.fieldName=='email' " +
                        "&& @.message=='Invalid email format.')]").exists())

        ;


    }

    @Test
    void testSaveWithEmptyNameThenThrowBadRequest() throws Exception {
        final var entity = generateMock(CreateUserRequest.class)
                .withName("")
                .withEmail(EMAIL_VALID);


        mockMvc.perform(post(BASE_URI)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(toJson(entity))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Exception in validation attributes"))
                .andExpect(jsonPath("$.error").value("Validation Exception"))
                .andExpect(jsonPath("$.path").value(BASE_URI))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.errors[?(@.fieldName=='name' " +
                        "&& @.message=='Name is required, cannot be empty.')]").exists())
                .andExpect(jsonPath("$.errors[?(@.fieldName=='name' " +
                        "&& @.message=='Name must be between 3 and 50 characters.')]").exists())

        ;


    }



    private  String toJson(Object object) throws Exception {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new Exception("Error converting object to json", e);
        }
    }

}