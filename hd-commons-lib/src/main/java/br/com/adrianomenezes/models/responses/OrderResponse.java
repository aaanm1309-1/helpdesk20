package br.com.adrianomenezes.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public record OrderResponse(
        String id,
        String requesterId,
        String customerId,
        String title,
        String description,
        String status,
        String createdAt,
        String closedAt

) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}

