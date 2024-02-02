package br.com.adrianomenezes.userserviceapi.controller;


import br.com.adrianomenezes.userserviceapi.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/users")
public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable(name = "id") final String id);
}
