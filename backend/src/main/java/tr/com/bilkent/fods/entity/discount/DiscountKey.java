package tr.com.bilkent.fods.entity.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DiscountKey implements Serializable {
    private Long rid;

    private String title;
}
