package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.discount.Discount;
import tr.com.bilkent.fods.entity.discount.DiscountKey;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, DiscountKey> {
    @Query(value = "SELECT * FROM discount WHERE rid = ?1", nativeQuery = true)
    List<Discount> findDiscountsOfRestaurant(Restaurant restaurant);
}
