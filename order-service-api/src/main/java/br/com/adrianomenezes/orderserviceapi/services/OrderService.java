package br.com.adrianomenezes.orderserviceapi.services;

import br.com.adrianomenezes.models.requests.CreateOrderRequest;

public interface OrderService {
    void save(CreateOrderRequest request);
}
