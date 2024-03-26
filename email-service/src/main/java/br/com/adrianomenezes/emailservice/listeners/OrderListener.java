package br.com.adrianomenezes.emailservice.listeners;

import br.com.adrianomenezes.models.dtos.OrderCreatedMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class OrderListener {


    @RabbitListener(
            bindings = @QueueBinding(
            exchange = @Exchange(value = "helpdesk",type = "topic"),
            value = @Queue(value = "queue.orders"),
            key = "rk.orders.create")
    )
    public void listener(final OrderCreatedMessage message) {
        log.info("Ordem de Serviço processada/recebida com sucesso: {}", message);
    }
}
