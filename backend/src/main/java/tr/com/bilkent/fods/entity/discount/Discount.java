package tr.com.bilkent.fods.entity.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "discount")
public class Discount {
    @EmbeddedId
    private DiscountKey discountKey;

    @ManyToOne
    @MapsId("rid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "rid")
    private Restaurant restaurant;

    @NotNull
    private Integer percentage;

    @Column(length = 512)
    private String description;

    @NotNull
    @Column(name = "applies_min_amount")
    private Integer appliesMinAmount;

    @NotNull
    @Column(name = "active_from")
    private Date activeFrom;

    @NotNull
    @Column(name = "active_until")
    private Date activeUntil;
}
