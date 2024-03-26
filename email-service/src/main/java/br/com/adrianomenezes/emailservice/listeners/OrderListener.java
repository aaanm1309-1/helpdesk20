package br.com.adrianomenezes.emailservice.listeners;

//import br.com.adrianomenezes.emailservice.services.EmailService;
import br.com.adrianomenezes.emailservice.services.EmailService;
import br.com.adrianomenezes.models.dtos.OrderCreatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class OrderListener {

    private final EmailService service;


    @RabbitListener(
            bindings = @QueueBinding(
            exchange = @Exchange(value = "helpdesk",type = "topic"),
            value = @Queue(value = "queue.orders"),
            key = "rk.orders.create")
    )
    public void listener(final OrderCreatedMessage message) {
        log.info("Ordem de Serviço recebida: {}", message);
        service.sendMail(message);
    }
}
