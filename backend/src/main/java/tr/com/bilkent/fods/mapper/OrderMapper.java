package tr.com.bilkent.fods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.bilkent.fods.config.MapperConfig;
import tr.com.bilkent.fods.dto.order.OrderContentDTO;
import tr.com.bilkent.fods.dto.order.OrderDTO;
import tr.com.bilkent.fods.dto.order.OrderWithoutContentDTO;
import tr.com.bilkent.fods.entity.order.Order;
import tr.com.bilkent.fods.entity.ordercontent.OrderContent;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    List<OrderDTO> orderDtosFromOrders(List<Order> orders);

    @Mapping(target = "mealName", source = "meal.mealKey.name")
    OrderContentDTO orderContentDtoFromOrderContent(OrderContent orderContent);

    List<OrderContentDTO> orderContentDtosFromOrderContents(List<OrderContent> orderContents);

    OrderWithoutContentDTO orderWithoutContentDtoFromOrder(Order order);
}
