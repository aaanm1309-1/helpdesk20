package br.com.adrianomenezes.models.responses;

import lombok.Builder;
import lombok.With;

import java.io.Serial;
import java.io.Serializable;

@Builder
@With
public record AuthenticationResponse(
        String token,
        String refreshToken,
        String type

) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
