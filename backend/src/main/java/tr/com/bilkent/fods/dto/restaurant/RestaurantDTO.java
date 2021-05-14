package tr.com.bilkent.fods.dto.restaurant;

import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.bilkent.fods.dto.district.DistrictDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class RestaurantDTO {
    private Long rid;

    @NotNull
    private String name;

    private String description;

    private Boolean isFeatured;

    private String phoneNumber;

    @Valid
    private DistrictDTO address;

    private List<String> tags;
}
