package br.com.adrianomenezes.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Blob;

@Builder
public record CreateImageRequest(

        @Schema(description = "Customer ID", example = "65f25c44720bb339c1a46255")
        @NotBlank(message = "The customerId cannot be null or blank")
        @Size(min= 24, max= 36, message = "The customerId must be between 24 and 36 characters")
        String fileName,
        @Schema(description = "Title of Order", example = "Fix my computer")
        @NotBlank(message = "The title cannot be null or blank")
        @Size(min= 3, max= 45, message = "The title must be between 3 and 45 characters")
        String fileType,
        @Schema(description = "Description of order", example = "My computer is broken")
        @NotBlank(message = "The description cannot be null or blank")
        @Size(min= 10, max=60000, message = "The description must be between 10 and 3000 characters")
        Blob grpData


) implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
}