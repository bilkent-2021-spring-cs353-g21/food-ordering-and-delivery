package tr.com.bilkent.fods.dto.search;

import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.bilkent.fods.dto.district.DistrictDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SearchDTO {
    @NotNull
    @NotEmpty
    private String keyword;

    @Valid
    @NotNull
    private DistrictDTO district;

    // Filters
    private Boolean onlyServingDistrict;
    private Double scoreAtLeast;
    private Double minPrice;
    private Double maxPrice;
    private Double maxMinDeliveryCost;
}
