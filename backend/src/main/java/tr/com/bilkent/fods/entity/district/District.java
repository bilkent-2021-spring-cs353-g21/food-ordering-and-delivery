package tr.com.bilkent.fods.entity.district;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bilkent.fods.entity.serves.Serves;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "district")
public class District {
    @EmbeddedId
    private DistrictKey districtKey;

    @OneToMany(mappedBy = "district")
    private List<Serves> servedBy;
}
