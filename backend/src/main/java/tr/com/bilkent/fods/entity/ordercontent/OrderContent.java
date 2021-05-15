package tr.com.bilkent.fods.entity.ordercontent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import tr.com.bilkent.fods.entity.meal.Meal;
import tr.com.bilkent.fods.entity.order.Order;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "order_content")
public class OrderContent {
    @EmbeddedId
    private OrderContentKey orderContentKey;

    @ManyToOne
    @MapsId("oid")
    @JoinColumn(name = "oid")
    private Order order;

    @ManyToOne
    @MapsId("mealKey")
    @JoinColumns(value = {
            @JoinColumn(name = "rid", referencedColumnName = "rid"),
            @JoinColumn(name = "name", referencedColumnName = "name")
    }, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Meal meal;

    private int quantity;

    private double mealPrice;

    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @CollectionTable(name = "order_content_ingredients",
            joinColumns = {
                    @JoinColumn(name = "oid", referencedColumnName = "oid"),
                    @JoinColumn(name = "in_order_index", referencedColumnName = "in_order_index"),
                    @JoinColumn(name = "rid", referencedColumnName = "rid"),
                    @JoinColumn(name = "meal_name", referencedColumnName = "name")
            })
    @Column(name = "ingredient")
    private List<String> ingredients;
}
