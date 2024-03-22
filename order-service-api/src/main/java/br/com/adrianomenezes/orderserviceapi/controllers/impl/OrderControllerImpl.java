package br.com.adrianomenezes.orderserviceapi.controllers.impl;

import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.models.requests.UpdateOrderRequest;
import br.com.adrianomenezes.models.responses.OrderResponse;
import br.com.adrianomenezes.orderserviceapi.controllers.OrderController;
import br.com.adrianomenezes.orderserviceapi.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService service;

    @Override
    public ResponseEntity<Void> save(CreateOrderRequest request) {
        service.save(request);
        return ResponseEntity.status(CREATED.value()).build() ;
    }

    @Override
    public ResponseEntity<OrderResponse> update(Long id, UpdateOrderRequest request) {
        return ResponseEntity.ok().body(service.update(id,request));
    }
}
