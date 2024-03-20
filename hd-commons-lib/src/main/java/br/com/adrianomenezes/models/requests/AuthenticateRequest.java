package br.com.adrianomenezes.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public record AuthenticateRequest(
        @Schema(description = "User Email", example = "john@due.com")
        @Email(message = "Invalid email format.")
        @NotBlank(message = "Email is required, cannot be empty.")
        @Size(min = 6, max = 50, message = "Email must be between 6 and 50 characters.")
        String email,
        @Schema(description = "User Password", example = "123456")
        @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters.")
        @NotBlank(message = "Password is required, cannot be empty.")
        String password
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
