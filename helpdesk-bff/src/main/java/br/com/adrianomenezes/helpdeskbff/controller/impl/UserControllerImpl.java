package br.com.adrianomenezes.helpdeskbff.controller.impl;

import br.com.adrianomenezes.helpdeskbff.service.UserService;
import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.models.requests.UpdateUserRequest;
import br.com.adrianomenezes.models.responses.UserResponse;
import br.com.adrianomenezes.helpdeskbff.controller.UserController;
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
        return ResponseEntity.status(CREATED).build();
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @Override
    public ResponseEntity<UserResponse> update(final String id,
                                               final UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok().body(userService.update(id, updateUserRequest));
    }
}
