package br.com.adrianomenezes.userserviceapi.controller.impl;

import br.com.adrianomenezes.userserviceapi.controller.UserController;
import br.com.adrianomenezes.userserviceapi.entity.User;
import br.com.adrianomenezes.userserviceapi.repository.UserRepository;
import br.com.adrianomenezes.userserviceapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<User> findById(String id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }
}
