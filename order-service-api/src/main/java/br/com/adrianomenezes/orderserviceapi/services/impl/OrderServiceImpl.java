package br.com.adrianomenezes.orderserviceapi.services.impl;

import br.com.adrianomenezes.models.enums.OrderStatusEnum;
import br.com.adrianomenezes.models.exceptions.ResourceNotFoundException;
import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.models.requests.UpdateOrderRequest;
import br.com.adrianomenezes.models.responses.OrderResponse;
import br.com.adrianomenezes.orderserviceapi.entities.Order;
import br.com.adrianomenezes.orderserviceapi.mapper.OrderMapper;
import br.com.adrianomenezes.orderserviceapi.repositories.OrderRepository;
import br.com.adrianomenezes.orderserviceapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static br.com.adrianomenezes.models.enums.OrderStatusEnum.CLOSED;
import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    @Override
    public void save(CreateOrderRequest request) {

        final var entity = repository.save(mapper.fromRequest(request));
        log.info("Order created: {}",entity);
    }

    @Override
    public OrderResponse update(Long id, UpdateOrderRequest request) {
        Order order = findById(id);
        order = mapper.update(request, order);
        if (order.getStatus().equals(CLOSED.toString())){
            order.setClosedAt(now());
        }
        final var entity = repository.save(order);
        log.info("Order updated: {}",entity);
        return mapper.fromEntity(entity);
    }

    @Override
    public Order findById(final Long id) {
        return repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                      "Order not found. Id: " + id + ", Type: " + Order.class.getSimpleName())
        );
    }

    @Override
    public void deleById(Long id) {
        var entity = findById(id);
        repository.delete(entity);
    }


}
