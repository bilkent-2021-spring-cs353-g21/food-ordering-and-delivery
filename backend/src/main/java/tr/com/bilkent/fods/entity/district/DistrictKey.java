package tr.com.bilkent.fods.entity.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DistrictKey implements Serializable {
    @Column(name = "city_name")
    private String cityName;

    @Column(name = "district_name")
    private String districtName;
}
