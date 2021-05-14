package tr.com.bilkent.fods.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderContentDTO {
    private String mealName;

    private int quantity;

    private double mealPrice;

    private List<String> ingredients;
}
