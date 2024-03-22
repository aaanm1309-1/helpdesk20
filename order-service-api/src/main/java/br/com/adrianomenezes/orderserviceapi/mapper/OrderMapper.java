package br.com.adrianomenezes.orderserviceapi.mapper;

import br.com.adrianomenezes.models.enums.OrderStatusEnum;
import br.com.adrianomenezes.models.requests.CreateOrderRequest;
import br.com.adrianomenezes.models.responses.OrderResponse;
import br.com.adrianomenezes.orderserviceapi.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface OrderMapper {
    OrderResponse fromEntity(final Order entity);

    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    Order fromRequest(CreateOrderRequest request);

    List<OrderResponse> fromEntityList(List<Order> all);

    @Mapping(target = "id", ignore = true)
    Order update(CreateOrderRequest request, @MappingTarget Order orderOld);

//    @Named("mapStatus")
//    default OrderStatusEnum mapStatus(final String status) {
//        return OrderStatusEnum.toEnum(status);
//    }
}
