package br.com.adrianomenezes.emailservice.services;

import br.com.adrianomenezes.emailservice.models.OperationEnum;
import br.com.adrianomenezes.emailservice.utils.EmailUtils;
import br.com.adrianomenezes.models.dtos.OrderCreatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

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

    public void sendHtmlMail(
            final OrderCreatedMessage orderDTO, OperationEnum operation
    ) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        String process = getContext(orderDTO, operation);

        EmailUtils.getMimeMessage(message, process, orderDTO, "Ordem de serviço criada com sucesso");

        mailSender.send(message);
    }

    private String getContext(OrderCreatedMessage orderDTO, OperationEnum operation) {
        Context context = new Context();

        return switch (operation) {
            case ORDER_CREATED -> {
                log.info("Enviando email de criação de ordem de serviço");
                context = EmailUtils.getContextToCreatedOrder(orderDTO);
                yield templateEngine.process("email/order-created", context);
            }
            case ORDER_UPDATED -> {
                log.info("Enviando email de atualização de ordem de serviço");
//                 context = EmailUtils.getContextToUpdatedOrder(orderDTO);
                yield templateEngine.process("email/order-updated", context);
            }
            case ORDER_DELETED -> {
                log.info("Enviando email de exclusão de ordem de serviço");
                // context = EmailUtils.getContextToDeletedOrder(orderDTO);
                yield templateEngine.process("email/order-deleted", context);
            }
        };
    }


}
