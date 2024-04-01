package br.com.adrianomenezes.uploadfilerealtyimovelapi.controllers.impl;

import br.com.adrianomenezes.models.requests.CreateImageRequest;
import br.com.adrianomenezes.models.responses.ImageResponse;
import br.com.adrianomenezes.uploadfilerealtyimovelapi.controllers.ImageController;
import br.com.adrianomenezes.uploadfilerealtyimovelapi.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.sql.rowset.serial.SerialBlob;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class ImageControllerImpl implements ImageController {

    private final ImageService imageService;

    @Override
    public ResponseEntity<ImageResponse> findById(final Long id) {
        return ResponseEntity.ok().body(imageService.findById(id));
    }

    @Override
    public ResponseEntity<byte[]> findByFileName(String fileName) throws FileNotFoundException, SQLException {
        var fileUpload = imageService.findByFileName(fileName);
        var grpData = fileUpload.getGrpData();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, fileUpload.getFileType())

                .cacheControl(CacheControl.maxAge(Duration.ofSeconds(60)).cachePrivate().mustRevalidate())
                .body(grpData.getBytes(1L, (int) grpData.length()));

    }

    @Override
    public ResponseEntity<ImageResponse> save(final MultipartFile file) throws IOException, SQLException {
        var fileCreated = imageService.save(file);
        return ResponseEntity.status(CREATED.value()).body(
                fileCreated);

    }



    @Override
    public ResponseEntity<List<ImageResponse>> findAll() {

        return ResponseEntity.ok().body(imageService.findAll());
    }

//    @Override
//    public ResponseEntity<UserResponse> update(final String id,
//                                               final UpdateUserRequest updateUserRequest) {
//        return ResponseEntity.ok().body(userService.update(id, updateUserRequest));
//    }
}
