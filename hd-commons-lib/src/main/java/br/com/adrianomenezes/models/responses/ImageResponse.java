package br.com.adrianomenezes.models.responses;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Blob;

@Builder
public record ImageResponse(
        boolean isError,
        String fileName,
        String fileType,
        String fileLink,
        Blob fileImage,
        String createdAt

) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}

