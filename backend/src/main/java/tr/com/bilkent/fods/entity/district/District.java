package tr.com.bilkent.fods.entity.district;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "district")
public class District {
    @EmbeddedId
    private DistrictKey districtKey;
}
