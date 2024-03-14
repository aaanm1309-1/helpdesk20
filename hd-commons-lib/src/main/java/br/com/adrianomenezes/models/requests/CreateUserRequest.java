package br.com.adrianomenezes.models.requests;

import br.com.adrianomenezes.models.enums.ProfileEnum;
import lombok.With;

import java.util.Set;

@With
public record CreateUserRequest(
        String name,
        String email,
        String password,
        Set<ProfileEnum> profiles
) {

}
