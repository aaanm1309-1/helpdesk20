package br.com.adrianomenezes.emailservice.services;

import br.com.adrianomenezes.models.dtos.OrderCreatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${mail.text-created-order-confirmation}")
    private String textCreatedOrderConfirmation;

    public void sendMail(final OrderCreatedMessage order) {
        log.info("Enviando e-mail para o cliente: {}", order.getCustomer().email());
        SimpleMailMessage message = getSimpleMailMessage(order);

        try {
            mailSender.send(message);
            log.info("Email enviado para o cliente: {}", order.getCustomer().email());

        } catch(MailException e) {
            switch (e.getClass().getSimpleName()) {
                case "MailAuthenticationException":
                    log.error("Erro de autenticação ao enviar e-mail: {}", e.getMessage());
                    break;
                case "MailSendException":
                    log.error("Erro ao enviar e-mail: {}", e.getMessage());
                    break;
                default:
                    log.error("Erro inesperado ao enviar e-mail: {}", e.getMessage());
                    break;
            }
        }

    }

    private SimpleMailMessage getSimpleMailMessage(OrderCreatedMessage order) {
        String subject = "Ordem de serviço criada com sucesso";
        String textMessage = String.format(textCreatedOrderConfirmation,
               order.getCustomer().name(),
                order.getOrder().id(),
                order.getOrder().title(),
                order.getOrder().description(),
                order.getOrder().createdAt(),
                order.getOrder().status(),
                order.getRequester().name()
        );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setTo(order.getCustomer().email());
        message.setText(textMessage);
        return message;
    }


}
