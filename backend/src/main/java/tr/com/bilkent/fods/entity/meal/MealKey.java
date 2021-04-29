package tr.com.bilkent.fods.entity.meal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MealKey implements Serializable {
    private Long rid;

    private String name;

    public MealKey(String name) {
        this.name = name;
    }
}
