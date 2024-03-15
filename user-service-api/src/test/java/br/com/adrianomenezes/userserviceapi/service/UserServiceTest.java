package br.com.adrianomenezes.userserviceapi.service;

import br.com.adrianomenezes.models.exceptions.ResourceNotFoundException;
import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.models.requests.UpdateUserRequest;
import br.com.adrianomenezes.models.responses.UserResponse;
import br.com.adrianomenezes.userserviceapi.entity.User;
import br.com.adrianomenezes.userserviceapi.mapper.UserMapper;
import br.com.adrianomenezes.userserviceapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static br.com.adrianomenezes.userserviceapi.creator.CreatorUtils.generateMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @Mock
    private BCryptPasswordEncoder encoder;


    @Test
    void whenCallFindByIdWithValidIdThenReturnUserResponse() {
        when(repository.findById(anyString())).thenReturn(Optional.of(new User()));
        when(mapper.fromEntity(any(User.class))).thenReturn(
                mock(UserResponse.class)
        );

        final var response = userService.findById("1");

        assertNotNull(response);
        assertEquals(UserResponse.class, response.getClass());

        verify(repository, times(1)).findById(anyString());
    }

    @Test
    void whenCallFindByIdWithInvalidIdThenThrowResourceNotFoundException() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findById("2"));

        try {
            userService.findById("1");
            fail("ResourceNotFoundException not thrown");
        } catch (ResourceNotFoundException e) {
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals("User not found. Id: 1, Type: UserResponse", e.getMessage());

        }

        verify(repository, times(2)).findById(anyString());
        verify(mapper, never()).fromEntity(any(User.class));
    }

    @Test
    void whenCallFindAllThenReturnListOfUserResponse() {

        when(repository.findAll()).thenReturn(
                List.of(new User(), new User())
        );
        when(mapper.fromEntity(any(User.class))).thenReturn(
                generateMock(UserResponse.class)
        );

        final var response = userService.findAll();

        assertNotNull(response);
        assertEquals(UserResponse.class, response.get(0).getClass());
        assertEquals(2, response.size());

        verify(repository, times(1)).findAll();
        verify(mapper, times(2)).fromEntity(any(User.class));
    }

    @Test
    void whenCallSaveThenSaveUser() {
        final var request = generateMock(CreateUserRequest.class);
        when(mapper.fromRequest(request)).thenReturn(new User());
        when(encoder.encode(anyString())).thenReturn("encoded");
        when(repository.save(any(User.class))).thenReturn(new User());
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        userService.save(request);

        verify(mapper, times(1)).fromRequest(request);
        verify(encoder, times(1)).encode(request.password());
        verify(repository, times(1)).save(any(User.class));
        verify(repository).findByEmail(request.email());
    }

    @Test
    void whenCallSaveWithExistingEmailThenThrowResourceNotFoundException() {
        final var request = generateMock(CreateUserRequest.class);
        final var entity = generateMock(User.class);

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(entity));

        assertThrows(DataIntegrityViolationException.class, () -> userService.save(request));

        try {
            userService.save(request);
            fail("DataIntegrityViolationException not thrown");
        } catch (DataIntegrityViolationException e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("Email [ "+ request.email() + " ] already exists." , e.getMessage());
        }

        verify(repository, times(2)).findByEmail(request.email());
        verify(mapper, never()).fromRequest(request);
        verify(encoder, never()).encode(request.password());
        verify(repository, never()).save(any(User.class));
    }

    @Test
    void whenCallUpdateThenUpdateUser() {
        final var request = generateMock(UpdateUserRequest.class);
        final var entity = generateMock(User.class);

        when(repository.findById(anyString())).thenReturn(Optional.of(entity));

        when(mapper.update(any(), any())).thenReturn(entity);
        when(encoder.encode(anyString())).thenReturn("encoded");
        when(repository.save(any(User.class))).thenReturn(entity);
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        userService.update(entity.getId(), request);

        verify(repository, times(1)).findById(entity.getId());
        verify(mapper, times(1)).update(request, entity);
        verify(mapper, times(1)).fromEntity(entity);
        verify(encoder, times(1)).encode(request.password());
        verify(repository, times(1)).save(any(User.class));
        verify(repository).findByEmail(request.email());
    }

    @Test
    void whenCallUpdateWithExistingEmailThenThrowResourceNotFoundException() {
        final var request = generateMock(UpdateUserRequest.class);
        final var entity = generateMock(User.class);

        when(repository.findById(anyString())).thenReturn(Optional.of(entity));
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(entity));

        assertThrows(DataIntegrityViolationException.class, () -> userService.update("1", request));

        try {
            userService.update("1", request);
            fail("DataIntegrityViolationException not thrown");
        } catch (DataIntegrityViolationException e) {
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("Email [ "+ request.email() + " ] already exists." , e.getMessage());
        }

        verify(repository, times(2)).findById("1");
        verify(repository, times(2)).findByEmail(request.email());
        verify(mapper, never()).update(request, entity);
        verify(encoder, never()).encode(request.password());
        verify(repository, never()).save(entity);
    }


}