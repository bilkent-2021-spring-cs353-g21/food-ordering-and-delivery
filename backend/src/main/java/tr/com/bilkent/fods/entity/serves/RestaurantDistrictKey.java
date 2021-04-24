package tr.com.bilkent.fods.entity.serves;

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
public class RestaurantDistrictKey implements Serializable {
    private Long rid;

    private DistrictKey districtKey;
}
