package tr.com.bilkent.fods.entity.deliveryaddress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.bilkent.fods.entity.district.DistrictKey;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DeliveryAddressKey implements Serializable {
    private String username;

    private DistrictKey districtKey;
}
