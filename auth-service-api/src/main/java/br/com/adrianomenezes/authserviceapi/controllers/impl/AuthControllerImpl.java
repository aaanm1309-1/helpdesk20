package br.com.adrianomenezes.authserviceapi.controllers.impl;

import br.com.adrianomenezes.authserviceapi.controllers.AuthController;
import br.com.adrianomenezes.models.requests.AuthenticateRequest;
import br.com.adrianomenezes.models.responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {
    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticateRequest authRequest) {
        return ResponseEntity.ok().body(AuthenticationResponse.builder()
                        .type("Bearer")
                        .token("token")
                .build());
    }
}
