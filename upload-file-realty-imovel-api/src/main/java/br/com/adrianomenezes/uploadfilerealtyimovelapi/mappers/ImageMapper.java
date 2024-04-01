//package br.com.adrianomenezes.uploadfilerealtyimovelapi.mappers;
//
//
//import br.com.adrianomenezes.models.requests.CreateImageRequest;
//import br.com.adrianomenezes.models.responses.ImageResponse;
//import br.com.adrianomenezes.uploadfilerealtyimovelapi.entities.Image;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
//import static org.mapstruct.NullValuePropertyMappingStrategy.*;
//
//@Mapper(
//        componentModel = "spring",
//        nullValuePropertyMappingStrategy = IGNORE,
//        nullValueCheckStrategy = ALWAYS
//)
//public interface ImageMapper {
//    ImageResponse fromEntity(final Image entity);
//
//    @Mapping(target = "fileId", ignore = true)
//    Image fromRequest(CreateImageRequest createImageRequest);
//
//    List<ImageResponse> fromEntityList(List<Image> all);
//
////    @Mapping(target = "fileId", ignore = true)
////    Image update(UpdateUserRequest updateUserRequest, @MappingTarget Image imageOld);
//}
//
