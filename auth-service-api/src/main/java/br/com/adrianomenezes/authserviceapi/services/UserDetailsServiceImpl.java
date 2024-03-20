package br.com.adrianomenezes.authserviceapi.services;

import br.com.adrianomenezes.authserviceapi.reporitories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final var entity =
                repository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return null;
    }
}
