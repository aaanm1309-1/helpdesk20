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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public List<UserResponse> findAll() {
//        return userMapper.fromEntityList(
//                userRepository
//                        .findAll());
//
        return  repository
                .findAll()
                .stream().map(mapper::fromEntity)
                .toList();

    }

    public UserResponse findById(final String id) {
        return mapper.fromEntity(
                find(id));

    }

    public void save(CreateUserRequest request) {
        verifyIfEmailAlreadyExists(request.email(), null);
        repository.save(mapper.fromRequest(request)
                .withPassword(encoder.encode(request.password()))
        );
    }


    public UserResponse update(String id, UpdateUserRequest request) {

        User userOld = find(id);
        verifyIfEmailAlreadyExists(request.email(), id);
        return mapper.fromEntity(repository.save(
                mapper.update(request, userOld)
                        .withPassword(request.password() != null ?
                                encoder.encode(request.password())
                                : userOld.getPassword())
        ));
    }

    private User find(final String id) {
        return repository
                .findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("User not found. Id: " + id +", Type: " + UserResponse.class.getSimpleName()));
    }

    private void verifyIfEmailAlreadyExists(String email, final String id) {
        repository.findByEmail(email)
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new DataIntegrityViolationException("Email [ "+ email + " ] already exists."  );
                });
    }

}
