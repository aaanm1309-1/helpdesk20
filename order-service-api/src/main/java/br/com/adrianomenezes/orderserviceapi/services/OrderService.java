package br.com.adrianomenezes.orderserviceapi.services;

import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.models.requests.UpdateOrderRequest;
import br.com.adrianomenezes.models.responses.OrderResponse;
import br.com.adrianomenezes.orderserviceapi.entities.Order;

public interface OrderService {
    void save(CreateOrderRequest request);

    OrderResponse update(Long id, UpdateOrderRequest request);

    Order findById(final Long id);

    void deleById(Long id);
}
