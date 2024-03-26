package br.com.adrianomenezes.emailservice.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.protocol}")
    private String protocol;


    @Bean
    public JavaMailSenderImpl mailSender() {
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable",true);
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setProtocol(protocol);
        mailSender.setDefaultEncoding("utf-8");
        properties.setProperty("mail.mime.charset", "utf-8");
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }

}
