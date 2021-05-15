package tr.com.bilkent.fods.dto.meal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToBasketDTO {
    @NotNull
    private long rid;

    @NotNull
    private String name;

    @NotNull
    private int quantity;

    private List<String> ingredients;
}
