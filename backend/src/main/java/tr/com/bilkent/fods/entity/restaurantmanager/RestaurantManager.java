package tr.com.bilkent.fods.entity.restaurantmanager;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bilkent.fods.entity.User;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "restaurant_manager")
public class RestaurantManager extends User {
}
