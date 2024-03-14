package br.com.adrianomenezes.userserviceapi.service;

import br.com.adrianomenezes.models.exceptions.ResourceNotFoundException;
import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.models.requests.UpdateUserRequest;
import br.com.adrianomenezes.models.responses.UserResponse;
import br.com.adrianomenezes.userserviceapi.entity.User;
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
//        return userMapper.fromEntityList(
//                userRepository
//                        .findAll());
//
        return  userRepository
                .findAll()
                .stream().map(userMapper::fromEntity)
                .toList();

    }

    public UserResponse findById(final String id) {
        return userMapper.fromEntity(
                find(id));

    }

    public void save(CreateUserRequest createUserRequest) {
        verifyIfEmailAlreadyExists(createUserRequest.email(), null);
        userRepository.save(userMapper.fromRequest(createUserRequest));
    }


    public UserResponse update(String id, UpdateUserRequest updateUserRequest) {

        User userOld = find(id);
        verifyIfEmailAlreadyExists(updateUserRequest.email(), id);
        return userMapper.fromEntity(userRepository.save(userMapper.update(updateUserRequest, userOld)));
    }

    private User find(final String id) {
        return userRepository
                .findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("User not found. Id: " + id +", Type: " + UserResponse.class.getSimpleName()));
    }

    private void verifyIfEmailAlreadyExists(String email, final String id) {
        userRepository.findByEmail(email)
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new DataIntegrityViolationException("Email [ "+ email + " ] already exists."  );
                });
    }

}
