package tr.com.bilkent.fods.entity.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.deliveryaddress.DeliveryAddress;
import tr.com.bilkent.fods.entity.district.District;
import tr.com.bilkent.fods.entity.enumconverter.OrderStatusConverter;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    @Column(name = "placed_time")
    private Timestamp placedTime;

    @Column(name = "requested_delivery_time")
    private Timestamp requestedDeliveryTime;

    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "from_restaurant")
    private Restaurant fromRestaurant;

    private Double cost;

    @ManyToOne
    @JoinColumn(name = "belongs_to")
    private Customer belongsTo;

    @ManyToOne
    private DeliveryAddress destination;
}
