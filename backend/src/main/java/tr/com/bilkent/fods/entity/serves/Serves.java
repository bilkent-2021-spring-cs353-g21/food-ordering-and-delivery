package tr.com.bilkent.fods.entity.serves;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bilkent.fods.entity.district.District;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "serves")
public class Serves {
    @EmbeddedId
    private RestaurantDistrictKey restaurantDistrictKey;

    @ManyToOne
    @MapsId("rid")
    @JoinColumn(name = "rid")
    private Restaurant restaurant;

    @ManyToOne
    @MapsId("districtKey")
    @JoinColumn(name = "city_name")
    @JoinColumn(name = "district_name")
    private District district;

    @Column(name = "min_amount")
    private Integer minAmount;
}
