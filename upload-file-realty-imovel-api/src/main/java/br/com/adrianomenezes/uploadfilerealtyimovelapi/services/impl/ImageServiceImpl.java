package br.com.adrianomenezes.uploadfilerealtyimovelapi.services.impl;

import br.com.adrianomenezes.models.exceptions.ResourceNotFoundException;
import br.com.adrianomenezes.models.responses.ImageResponse;
import br.com.adrianomenezes.uploadfilerealtyimovelapi.entities.Image;
import br.com.adrianomenezes.uploadfilerealtyimovelapi.repositories.ImageRepository;
import br.com.adrianomenezes.uploadfilerealtyimovelapi.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.sql.rowset.serial.SerialBlob;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;
//    private final BCryptPasswordEncoder encoder;

    @Override
    public List<ImageResponse> findAll() {
        return null;
//        return  repository
//                .findAll()
//                .stream().map(mapper::fromEntity)
//                .toList();

    }

    public ImageResponse findById(final Long id) {
        var imageReturned = find(id);
        return  ImageResponse.builder()
                .fileName(imageReturned.getFileName())
                .fileType(imageReturned.getFileType())
                .build();

    }

//    public void save(CreateImageRequest request) {
////        verifyIfEmailAlreadyExists(request.email(), null);
////        repository.save(mapper.fromRequest(request)
////                .withPassword(encoder.encode(request.password()))
////        );
//    }

    public ImageResponse save(MultipartFile file) throws IOException, SQLException {
        if(repository.existsImageByFileName(file.getOriginalFilename())){
            throw new DataIntegrityViolationException("Image exist: "+file.getOriginalFilename());
        }
        var image = Image.builder()
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
//                .grpData(file.getBytes())
                .grpData(new SerialBlob(file.getBytes()))
//                .grpData(new SerialBlob(file.getInputStream().readAllBytes()))
                .build();

        var newImage = repository.save(image);

        return ImageResponse.builder()
                .fileName(newImage.getFileName())
                .fileType(newImage.getFileType())
                .fileLink(creteUploadFileLink(newImage.getFileName()))
                .build();

    }

    private String creteUploadFileLink(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/api/v1/files/byname/" + fileName).toUriString();
    }

    @Override
    public Image findByFileName(String fileName) throws FileNotFoundException {
                return repository.findByFileName(fileName)
                                .orElseThrow(FileNotFoundException::new);

//                return ImageResponse.builder()
//                        .isError(false)
//                        .
//        .build();
    }

    //
//    public UserResponse update(String id, UpdateUserRequest request) {
//
//        User userOld = find(id);
//        verifyIfEmailAlreadyExists(request.email(), id);
//        return mapper.fromEntity(repository.save(
//                mapper.update(request, userOld)
//                        .withPassword(request.password() != null ?
//                                encoder.encode(request.password())
//                                : userOld.getPassword())
//        ));
//    }
//
    private Image find(final Long id) {
        return repository
                .findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Image not found. Id: " + id +", Type: " + ImageResponse.class.getSimpleName()));
    }

//
//    private void verifyIfEmailAlreadyExists(String email, final String id) {
//        repository.findByEmail(email)
//                .filter(user -> !user.getId().equals(id))
//                .ifPresent(user -> {
//                    throw new DataIntegrityViolationException("Email [ "+ email + " ] already exists."  );
//                });
//    }

}
