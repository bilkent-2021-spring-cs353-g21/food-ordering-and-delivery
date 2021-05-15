package tr.com.bilkent.fods.dto.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultDTO {
    private List<RestaurantResultDTO> restaurants;
    private MealSearchResultsDTO mealResults;
}
