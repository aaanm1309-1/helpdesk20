package br.com.adrianomenezes.userserviceapi.controller;


import br.com.adrianomenezes.models.exceptions.StandardError;
import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.models.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "UserController", description = "Controller responsible for user operations")
@RequestMapping("/api/v1/users")
public interface UserController {

    @Operation(summary = "Find user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(
            @Parameter(description = "User id", required = true, example = "65bc391ab0973863d7ff8cab")
            @PathVariable(name = "id") final String id
    );

    @Operation(summary = "Save new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            )
    })
    @PostMapping
    ResponseEntity<Void> save(
//            @Parameter(description = "User id", required = true, example = "65bc391ab0973863d7ff8cab")
            @RequestBody final CreateUserRequest createUserRequest
    );


    @Operation(summary = "Find user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping
    ResponseEntity<List<UserResponse>> findAll(
    );

}
