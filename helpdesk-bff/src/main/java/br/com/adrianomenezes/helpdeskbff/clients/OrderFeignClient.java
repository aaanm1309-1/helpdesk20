package br.com.adrianomenezes.helpdeskbff.clients;

import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.models.requests.UpdateOrderRequest;
import br.com.adrianomenezes.models.responses.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "order-service-api",
        path = "api/v1/orders"
)
public interface OrderFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<OrderResponse> findById(
            @PathVariable(name = "id") final Long id
    );


    @PostMapping
    ResponseEntity<Void> save(
            @Valid
            @RequestBody final CreateOrderRequest request
    );


    @PutMapping("/{id}")
    ResponseEntity<OrderResponse> update(
            @PathVariable(name = "id") Long id,
            @Valid
            @RequestBody final UpdateOrderRequest request
    );




    @GetMapping("/page")
    ResponseEntity<Page<OrderResponse>> findAllPaginated(
            @RequestParam(name = "page",defaultValue = "0") final Integer page,
            @RequestParam(name = "linesPerPage",defaultValue = "10") final Integer linesPerPage,
            @RequestParam(name = "direction",defaultValue = "ASC") final String direction,
            @RequestParam(name = "orderBy",defaultValue = "id") final String orderBy
    );


    @GetMapping
    ResponseEntity<List<OrderResponse>> findAll();



    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(
            @PathVariable(name = "id") final Long id
    );

}
