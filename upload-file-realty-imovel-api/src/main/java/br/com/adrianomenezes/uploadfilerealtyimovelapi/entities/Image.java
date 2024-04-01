package br.com.adrianomenezes.uploadfilerealtyimovelapi.entities;

import br.com.adrianomenezes.models.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_image")
@Builder
public class Image implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String fileName;

    @Column(nullable = false,length = 50)
    private String fileType;

    @Column(nullable = false)
    @Lob
    private Blob grpData;
//    @Lob
//    private byte[] grpData;
////    private Blob grpData;

    @Builder.Default
    private LocalDateTime createdAt = now();

}
