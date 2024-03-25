package br.com.adrianomenezes.models.dtos;

import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.models.responses.OrderResponse;
import br.com.adrianomenezes.models.responses.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class OrderCreatedMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1l;

    private OrderResponse order;
    private UserResponse customer;
    private UserResponse requester;


}
