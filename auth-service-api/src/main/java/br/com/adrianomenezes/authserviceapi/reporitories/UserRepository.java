package br.com.adrianomenezes.authserviceapi.reporitories;

import br.com.adrianomenezes.authserviceapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>{

    Optional<User> findByEmail(final String email);
}
