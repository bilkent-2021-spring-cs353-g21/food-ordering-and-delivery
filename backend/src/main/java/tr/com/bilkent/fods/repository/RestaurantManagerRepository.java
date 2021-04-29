package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;

public interface RestaurantManagerRepository extends JpaRepository<RestaurantManager, String> {
}
