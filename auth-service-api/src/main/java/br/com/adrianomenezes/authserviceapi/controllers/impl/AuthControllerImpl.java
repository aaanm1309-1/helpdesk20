package br.com.adrianomenezes.authserviceapi.controllers.impl;

import br.com.adrianomenezes.authserviceapi.controllers.AuthController;
import br.com.adrianomenezes.authserviceapi.security.JWTAuthenticationImpl;
import br.com.adrianomenezes.authserviceapi.services.RefreshTokenService;
import br.com.adrianomenezes.authserviceapi.utils.JWTUtils;
import br.com.adrianomenezes.models.requests.AuthenticateRequest;
import br.com.adrianomenezes.models.requests.RefreshTokenRequest;
import br.com.adrianomenezes.models.responses.AuthenticationResponse;
import br.com.adrianomenezes.models.responses.RefreshTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final JWTUtils jwtUtils;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final RefreshTokenService service;

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticateRequest authRequest) throws Exception {
        return ResponseEntity.ok().body(
                new JWTAuthenticationImpl(jwtUtils,authenticationConfiguration.getAuthenticationManager())
                        .authenticate(authRequest)
                        .withRefreshToken(service.save(authRequest.email()).getId())
                );
    }

    @Override
    public ResponseEntity<RefreshTokenResponse> refreshToken(RefreshTokenRequest request) {
        return ResponseEntity.ok().body(
                service.refreshToken(request.refreshToken())
        );
    }

}
