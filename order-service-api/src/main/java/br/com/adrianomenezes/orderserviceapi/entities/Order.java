package br.com.adrianomenezes.orderserviceapi.entities;

import br.com.adrianomenezes.models.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static br.com.adrianomenezes.models.enums.OrderStatusEnum.OPEN;
import static java.time.LocalDateTime.now;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_order")
@Builder
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 45)
    private String requesterId;

    @Column(nullable = false,length = 45)
    private String customerId;

    @Column(nullable = false,length = 50)
    private String title;

    @Column(nullable = false,length = 3000)
    private String description;

    @Column(nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status = OPEN;

    @Builder.Default
    private LocalDateTime createdAt = now();
    private LocalDateTime closedAt;


}
