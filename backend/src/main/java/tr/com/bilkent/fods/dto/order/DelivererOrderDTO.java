package tr.com.bilkent.fods.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelivererOrderDTO {
    private Long oid;

    private Timestamp placedTime;

    private Timestamp requestedDeliveryTime;

    private Double cost;

    private String restaurantName;

    private DeliveryAddressDTO destination;
}
