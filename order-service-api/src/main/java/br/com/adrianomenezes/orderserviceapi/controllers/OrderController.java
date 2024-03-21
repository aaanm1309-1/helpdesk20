package br.com.adrianomenezes.orderserviceapi.controllers;

import br.com.adrianomenezes.models.exceptions.StandardError;
import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.models.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "OrderController", description = "Controller responsible for orders operations")
@RequestMapping("/api/v1/orders")
public interface OrderController {

//    @Operation(summary = "Find user by id")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "User found"),
//            @ApiResponse(responseCode = "404",
//                    description = "User not found",
//                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = StandardError.class))
//            ),
//            @ApiResponse(responseCode = "500",
//                    description = "Internal server error",
//                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = StandardError.class))
//            )
//    })
//    @GetMapping("/{id}")
//    ResponseEntity<UserResponse> findById(
//            @Parameter(description = "User id", required = true, example = "65bc391ab0973863d7ff8cab")
//            @PathVariable(name = "id") final String id
//    );

    @Operation(summary = "Save new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created"),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Not found",
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
            @Valid
            @RequestBody final CreateOrderRequest request
    );
}
