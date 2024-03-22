package br.com.adrianomenezes.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public record UpdateOrderRequest(

        @Schema(description = "Requester ID", example = "65f25c10720bb339c1a46254")
        @Size(min= 24, max= 36, message = "The requesterId must be between 24 and 36 characters")
        String requesterId,
        @Schema(description = "Customer ID", example = "65f25c44720bb339c1a46255")
        @Size(min= 24, max= 36, message = "The customerId must be between 24 and 36 characters")
        String customerId,
        @Schema(description = "Title of Order", example = "Fix my computer")
        @Size(min= 3, max= 45, message = "The title must be between 3 and 45 characters")
        String title,
        @Schema(description = "Description of order", example = "My computer is broken")
        @Size(min= 10, max=3000, message = "The description must be between 10 and 3000 characters")
        String description,

        @Schema(description = "Status of order", example = "Open")
        @Size(min= 4, max= 15, message = "The status must be between 4 and 15 characters")
        String status
) implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
}