package br.com.adrianomenezes.authserviceapi.security;

import br.com.adrianomenezes.authserviceapi.security.dtos.UserDetailsDTO;
import br.com.adrianomenezes.authserviceapi.utils.JWTUtils;
import br.com.adrianomenezes.models.requests.AuthenticateRequest;
import br.com.adrianomenezes.models.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Log4j2
@RequiredArgsConstructor
public class JWTAuthenticationImpl {

    private final JWTUtils jwtUtils;
    private final AuthenticationManager manager;


    public AuthenticationResponse authenticate(final AuthenticateRequest request) {
        try{
            log.info("Authenticating user: {}", request.email());
            final var authResult = manager.authenticate(
                    new UsernamePasswordAuthenticationToken( request.email(), request.password())
            );
            return buildAuthenticationResponse((UserDetailsDTO) authResult.getPrincipal());
        } catch (BadCredentialsException ex){
            log.error("Error on authenticate user : {}", request.email());
            throw new BadCredentialsException("Email or password invalid");

        }
    }

    protected AuthenticationResponse buildAuthenticationResponse(final UserDetailsDTO detailsDTO) {
        log.info("Sucessfully authenticated user: {}", detailsDTO.getUsername());

        final var token = jwtUtils.generateToken(detailsDTO);
        return AuthenticationResponse.builder()
                .type("JWT")
                .token("Bearer " + token)
                .build();
    }
}
