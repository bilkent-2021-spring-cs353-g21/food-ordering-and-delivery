package tr.com.bilkent.fods.dto.meal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketContentDTO {
    private int inOrderIndex;

    private long rid;

    @NotNull
    private String name;

    private String description;

    private Double price;

    @NotNull
    private int quantity;

    private List<String> ingredients;
}
