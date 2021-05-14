package tr.com.bilkent.fods.dto.district;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class DistrictDTO {
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String cityName;

    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String districtName;
}
