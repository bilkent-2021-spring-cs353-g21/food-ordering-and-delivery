package tr.com.bilkent.fods.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealSearchResultsDTO {
    private Long totalMeals;
    private int totalPages;
    private List<MealResultDTO> meals;
}
