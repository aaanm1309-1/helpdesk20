package br.com.adrianomenezes.orderserviceapi.services.impl;

import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.orderserviceapi.mapper.OrderMapper;
import br.com.adrianomenezes.orderserviceapi.repositories.OrderRepository;
import br.com.adrianomenezes.orderserviceapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
}
