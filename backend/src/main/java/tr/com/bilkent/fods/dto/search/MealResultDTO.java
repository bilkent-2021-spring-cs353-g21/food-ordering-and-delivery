package tr.com.bilkent.fods.dto.search;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MealResultDTO {
    private Long rid;
    private String restaurantName;
    private String mealName;

    private String description;
    private Double price;
}
