package tr.com.bilkent.fods.entity.enumconverter;

import lombok.AllArgsConstructor;
import tr.com.bilkent.fods.entity.order.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@AllArgsConstructor
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        return (orderStatus == null) ? null : orderStatus.value();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String status) {
        return OrderStatus.get(status);
    }
}