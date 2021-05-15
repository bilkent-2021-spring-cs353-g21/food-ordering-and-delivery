package tr.com.bilkent.fods.entity.meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "meal")
public class Meal {
    @EmbeddedId
    private MealKey mealKey;

    @ManyToOne
    @MapsId("rid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rid")
    private Restaurant restaurant;

    private String type;

    @Column(length = 512)
    private String description;

    private Double price;

    @ElementCollection
    @CollectionTable(name = "meal_ingredients",
            joinColumns = {@JoinColumn(name = "rid", referencedColumnName = "rid"),
                    @JoinColumn(name = "meal_name", referencedColumnName = "name")})
    @Column(name = "ingredient")
    private List<String> ingredients;
}
