package br.com.adrianomenezes.uploadfilerealtyimovelapi.repositories;

import br.com.adrianomenezes.models.responses.ImageResponse;
import br.com.adrianomenezes.uploadfilerealtyimovelapi.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

//    boolean checkExistedFileByName(String fileName);

    boolean existsImageByFileName(String fileName);

    Optional<Image> findByFileName(String fileName);
}
