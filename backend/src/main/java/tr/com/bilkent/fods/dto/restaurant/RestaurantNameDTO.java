package tr.com.bilkent.fods.dto.restaurant;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RestaurantNameDTO {
    @NotNull
    private Long rid;

    @NotNull
    private String name;
}
