package br.com.adrianomenezes.helpdeskbff.services;

import br.com.adrianomenezes.helpdeskbff.clients.OrderFeignClient;
import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.models.requests.UpdateOrderRequest;
import br.com.adrianomenezes.models.responses.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService {

    private final OrderFeignClient orderFeignClient;


    public void save(CreateOrderRequest request) {
        orderFeignClient.save(request);

    }


    public OrderResponse update(Long id, UpdateOrderRequest request) {

        return orderFeignClient.update(id, request).getBody();
    }


    public OrderResponse findById(final Long id) {
        return orderFeignClient.findById(id).getBody();
    }


    public void deleById(Long id) {
         orderFeignClient.deleteById(id);
    }


    public List<OrderResponse> findAll() {

        return orderFeignClient.findAll().getBody();
    }


    public Page<OrderResponse> findAllPaginated(Integer page, Integer linesPerPage, String direction, String orderBy) {
        return orderFeignClient.findAllPaginated(page,linesPerPage,direction, orderBy).getBody();

    }



}
