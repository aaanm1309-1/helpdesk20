package br.com.adrianomenezes.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RefreshTokenRequest(
        @Size(min = 16,max = 50, message = "RefreshToken deve ter entre 16 e 40 caracteres" )
        @NotBlank(message = "Refresh token is required")
        String refreshToken
) {
}
