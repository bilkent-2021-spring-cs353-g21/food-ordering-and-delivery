package tr.com.bilkent.fods.entity.restaurantmanager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bilkent.fods.entity.User;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "restaurant_manager")
public class RestaurantManager extends User {
    @OneToMany(mappedBy = "manager")
    private List<Restaurant> restaurants;
}
