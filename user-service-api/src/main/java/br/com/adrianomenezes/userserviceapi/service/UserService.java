package br.com.adrianomenezes.userserviceapi.service;

import br.com.adrianomenezes.userserviceapi.entity.User;
import br.com.adrianomenezes.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(final String id) {
        return userRepository.findById(id)
                .orElse(null)
                ;
    }
}
