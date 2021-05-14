package tr.com.bilkent.fods.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.bilkent.fods.entity.order.OrderStatus;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderWithoutContentDTO {
    private Long oid;

    private Timestamp placedTime;

    private Timestamp requestedDeliveryTime;

    private OrderStatus status;

    private Double cost;
}
