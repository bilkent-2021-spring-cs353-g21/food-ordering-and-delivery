package tr.com.bilkent.fods.dto.meal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealDTO {
    @NotNull
    private String name;

    private String type;

    @Length(max = 512)
    private String description;

    @NotNull
    private Double price;

    private List<String> ingredients;
}
