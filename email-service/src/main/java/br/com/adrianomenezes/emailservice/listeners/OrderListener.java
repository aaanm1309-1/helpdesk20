package br.com.adrianomenezes.emailservice.listeners;

//import br.com.adrianomenezes.emailservice.services.EmailService;
import br.com.adrianomenezes.emailservice.models.OperationEnum;
import br.com.adrianomenezes.emailservice.services.EmailService;
import br.com.adrianomenezes.models.dtos.OrderCreatedMessage;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static br.com.adrianomenezes.emailservice.models.OperationEnum.ORDER_CREATED;

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
    public void listener(final OrderCreatedMessage message) throws MessagingException {
        log.info("Ordem de Serviço recebida: {}", message);
        service.sendMail(message);
        service.sendHtmlMail(message, ORDER_CREATED);
    }
}
