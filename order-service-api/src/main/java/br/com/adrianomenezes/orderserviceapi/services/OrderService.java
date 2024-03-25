package br.com.adrianomenezes.orderserviceapi.services;

import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.models.requests.UpdateOrderRequest;
import br.com.adrianomenezes.models.responses.OrderResponse;
import br.com.adrianomenezes.orderserviceapi.entities.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    void save(CreateOrderRequest request);

    OrderResponse update(Long id, UpdateOrderRequest request);

    Order findById(final Long id);

    void deleById(Long id);

    List<Order> findAll();

    Page<Order> findAllPaginated(Integer page, Integer linesPerPage, String direction, String orderBy);
}
