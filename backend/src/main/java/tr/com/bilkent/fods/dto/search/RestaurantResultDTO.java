package tr.com.bilkent.fods.dto.search;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RestaurantResultDTO {
    private Long rid;
    private String name;
    private String description;
    private Double minDeliveryCost;
    private Double score;
    private List<String> tags;
}
