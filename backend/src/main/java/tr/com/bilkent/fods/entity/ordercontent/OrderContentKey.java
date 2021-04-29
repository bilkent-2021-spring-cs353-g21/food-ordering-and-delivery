package tr.com.bilkent.fods.entity.ordercontent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.bilkent.fods.entity.meal.MealKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderContentKey implements Serializable {
    private Long oid;

    private MealKey mealKey;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "in_order_index")
    private int inOrderIndex;
}
