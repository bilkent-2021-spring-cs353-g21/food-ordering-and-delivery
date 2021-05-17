package tr.com.bilkent.fods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.bilkent.fods.config.MapperConfig;
import tr.com.bilkent.fods.dto.order.DelivererOrderDTO;
import tr.com.bilkent.fods.dto.order.OrderContentDTO;
import tr.com.bilkent.fods.dto.order.OrderDTO;
import tr.com.bilkent.fods.dto.order.OrderWithoutContentDTO;
import tr.com.bilkent.fods.entity.order.Order;
import tr.com.bilkent.fods.entity.ordercontent.OrderContent;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    List<OrderDTO> ordersToOrderDtos(List<Order> orders);

    @Mapping(target = "mealName", source = "meal.mealKey.name")
    OrderContentDTO orderContentToOrderContentDto(OrderContent orderContent);

    List<OrderContentDTO> orderContentsToOrderContentDtos(List<OrderContent> orderContents);

    OrderWithoutContentDTO orderToOrderWithoutContentDto(Order order);

    @Mapping(target = "restaurantName", source = "fromRestaurant.name")
    DelivererOrderDTO orderToDelivererOrderDto(Order order);

    List<DelivererOrderDTO> ordersToDelivererOrderDtos(List<Order> orders);
}
