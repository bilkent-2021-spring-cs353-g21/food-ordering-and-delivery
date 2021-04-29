package tr.com.bilkent.fods.entity.deliveryaddress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.district.District;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "delivery_address")
public class DeliveryAddress {
    @EmbeddedId
    private DeliveryAddressKey deliveryAddressKey;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    private Customer customer;

    @ManyToOne
    @MapsId("districtKey")
    @JoinColumn(name = "city_name")
    @JoinColumn(name = "district_name")
    private District district;

    private String title;

    @Column(length = 512)
    private String description;
}
