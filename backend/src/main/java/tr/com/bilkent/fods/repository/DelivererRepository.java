package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.deliverer.Deliverer;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import java.util.List;

public interface DelivererRepository extends JpaRepository<Deliverer, String> {
    @Query(value = "SELECT * FROM deliverer WHERE username IN " +
            "(SELECT deliverer_username FROM works_with WHERE restaurant_id = ?1)", nativeQuery = true)
    List<Deliverer> getDeliverersWorkingFor(Restaurant restaurant);
}
