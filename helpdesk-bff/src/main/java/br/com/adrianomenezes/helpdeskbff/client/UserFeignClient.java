package br.com.adrianomenezes.helpdeskbff.client;

import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.models.requests.UpdateUserRequest;
import br.com.adrianomenezes.models.responses.UserResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service-api",
        path = "api/v1/users"
)
public interface UserFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable(name = "id") final String id);

    @PostMapping
    ResponseEntity<Void> save(@Valid @RequestBody final CreateUserRequest createUserRequest);

    @GetMapping
    ResponseEntity<List<UserResponse>> findAll();

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> update(
            @PathVariable(name = "id") final String id,
            @Valid
            @RequestBody final UpdateUserRequest updateUserRequest
    );

}
