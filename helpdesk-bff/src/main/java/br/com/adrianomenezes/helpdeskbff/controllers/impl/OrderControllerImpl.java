package br.com.adrianomenezes.helpdeskbff.controllers.impl;

import br.com.adrianomenezes.helpdeskbff.services.OrderService;
import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.models.requests.UpdateOrderRequest;
import br.com.adrianomenezes.models.responses.OrderResponse;
import br.com.adrianomenezes.helpdeskbff.controllers.OrderController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService service;

    @Override
    public ResponseEntity<OrderResponse> findById(Long id) {
        return ResponseEntity.ok().body(
                service.findById(id)
        );
    }

    @Override
    public ResponseEntity<Void> save(CreateOrderRequest request) {
        service.save(request);
        return ResponseEntity.status(CREATED.value()).build() ;
    }

    @Override
    public ResponseEntity<OrderResponse> update(Long id, UpdateOrderRequest request) {
        return ResponseEntity.ok().body(service.update(id,request));
    }

    @Override
    public ResponseEntity<Page<OrderResponse>> findAllPaginated(Integer page, Integer linesPerPage,
                                                                      String direction, String orderBy) {
        return ResponseEntity.ok().body(
                service.findAllPaginated( page, linesPerPage, direction, orderBy)
        );

    }

    @Override
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok().body(
                service.findAll()
        );
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        service.deleById(id);
        return ResponseEntity.noContent().build();
    }


}
