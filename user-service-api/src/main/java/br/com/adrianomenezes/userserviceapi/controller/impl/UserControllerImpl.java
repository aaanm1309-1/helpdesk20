package br.com.adrianomenezes.userserviceapi.controller.impl;

import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.models.responses.UserResponse;
import br.com.adrianomenezes.userserviceapi.controller.UserController;
import br.com.adrianomenezes.userserviceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponse> findById(final String id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @Override
    public ResponseEntity<Void> save(final CreateUserRequest createUserRequest) {
        userService.save(createUserRequest);
        return ResponseEntity.status(CREATED.value()).build();
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }
}
