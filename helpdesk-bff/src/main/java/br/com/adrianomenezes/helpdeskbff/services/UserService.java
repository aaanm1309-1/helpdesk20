package br.com.adrianomenezes.helpdeskbff.services;

import br.com.adrianomenezes.helpdeskbff.clients.UserFeignClient;
import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.models.requests.UpdateUserRequest;
import br.com.adrianomenezes.models.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFeignClient userFeignClient;

    public List<UserResponse> findAll() {
        return userFeignClient.findAll().getBody();
    }

    public UserResponse findById(final String id) {
        return userFeignClient.findById(id).getBody();

    }

    public void save(CreateUserRequest request) {
        userFeignClient.save(request);
    }


    public UserResponse update(String id, UpdateUserRequest request) {

        return userFeignClient.update(id,request).getBody();
    }


}
