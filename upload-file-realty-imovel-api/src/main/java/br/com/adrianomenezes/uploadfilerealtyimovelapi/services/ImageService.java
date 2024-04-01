package br.com.adrianomenezes.uploadfilerealtyimovelapi.services;

import br.com.adrianomenezes.models.requests.CreateImageRequest;
import br.com.adrianomenezes.models.responses.ImageResponse;
import br.com.adrianomenezes.uploadfilerealtyimovelapi.entities.Image;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public interface ImageService {

     List<ImageResponse> findAll();

     ImageResponse findById(final Long id) ;
//     void save(final CreateImageRequest request) ;

     ImageResponse save(final MultipartFile file) throws IOException, SQLException;

     Image findByFileName(String fileName) throws FileNotFoundException;
//
//
//    public UserResponse update(String id, UpdateUserRequest request) ;
//
//    private User find(final String id) ;
//


}
