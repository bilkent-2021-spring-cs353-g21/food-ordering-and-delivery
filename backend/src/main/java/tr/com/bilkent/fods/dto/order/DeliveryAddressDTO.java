package tr.com.bilkent.fods.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddressDTO {
    private String cityName;
    private String districtName;
    private String description;
}
