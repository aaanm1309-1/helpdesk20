package br.com.adrianomenezes.userserviceapi.service;

import br.com.adrianomenezes.models.exceptions.ResourceNotFoundException;
import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.models.responses.UserResponse;
import br.com.adrianomenezes.userserviceapi.mapper.UserMapper;
import br.com.adrianomenezes.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> findAll() {
        return userMapper.fromEntityList(
                userRepository
                        .findAll());

    }

    public UserResponse findById(final String id) {
        return userMapper.fromEntity(
                userRepository
                        .findById(id)
                        .orElseThrow(()->
                                new ResourceNotFoundException("User not found. Id: " + id +", Type: " + UserResponse.class.getSimpleName())) );

    }

    public void save(CreateUserRequest createUserRequest) {
        verifyIfEmailAlreadyExists(createUserRequest.email(), null);
        userRepository.save(userMapper.fromRequest(createUserRequest));
    }

    private void verifyIfEmailAlreadyExists(String email, final String id) {
        userRepository.findByEmail(email)
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new DataIntegrityViolationException("Email [ "+ email + " ] already exists."  );
                });
    }
}
