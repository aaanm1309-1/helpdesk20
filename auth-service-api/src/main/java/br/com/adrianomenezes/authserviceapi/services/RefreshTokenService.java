package br.com.adrianomenezes.authserviceapi.services;

import br.com.adrianomenezes.authserviceapi.models.RefreshToken;
import br.com.adrianomenezes.authserviceapi.reporitories.RefreshTokenRepository;
import br.com.adrianomenezes.authserviceapi.security.dtos.UserDetailsDTO;
import br.com.adrianomenezes.authserviceapi.utils.JWTUtils;
import br.com.adrianomenezes.models.exceptions.RefreshTokenExpired;
import br.com.adrianomenezes.models.exceptions.ResourceNotFoundException;
import br.com.adrianomenezes.models.responses.RefreshTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.expiration-sec.refresh-token}")
    private Long expirationRefreshToken;

    private final RefreshTokenRepository repository;
    private final UserDetailsService userDetailsService;
    private final JWTUtils jwtUtils;

    public RefreshToken save(final String username) {
        return repository.save(
                RefreshToken.builder()
                        .id(UUID.randomUUID().toString())
                        .expiresAt(now().plusSeconds(expirationRefreshToken))
                        .username(username)
                        .build()
        );
    }

    public RefreshTokenResponse refreshToken(final String refreshTokenId){
        final var refreshToken = repository.findById(refreshTokenId)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found. Id: " + refreshTokenId));

        if(refreshToken.getExpiresAt().isBefore(now())){
            throw new RefreshTokenExpired("Refresh token expired. Id: " + refreshTokenId);
        }

        return new RefreshTokenResponse(
                jwtUtils.generateToken((UserDetailsDTO) userDetailsService
                        .loadUserByUsername(refreshToken.getUsername()))
        );
    }
}
