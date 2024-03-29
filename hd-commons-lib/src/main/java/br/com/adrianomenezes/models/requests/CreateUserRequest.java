package br.com.adrianomenezes.models.requests;

import br.com.adrianomenezes.models.enums.ProfileEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.With;

import java.util.Set;

@With
public record CreateUserRequest(
        @Schema(description = "User Name", example = "John Doe")
        @NotBlank(message = "Name is required, cannot be empty.")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
        String name,

        @Schema(description = "User Email", example = "john@due.com")
        @Email(message = "Invalid email format.")
        @NotBlank(message = "Email is required, cannot be empty.")
        @Size(min = 6, max = 50, message = "Email must be between 6 and 50 characters.")
        String email,
        @Schema(description = "User Password", example = "123456")
        @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters.")
        @NotBlank(message = "Password is required, cannot be empty.")
        String password,

        @Schema(description = "User Profiles", example = "[\"ROLE_ADMIN\", \"ROLE_CUSTOMER\"]")
        Set<ProfileEnum> profiles

) {

}
