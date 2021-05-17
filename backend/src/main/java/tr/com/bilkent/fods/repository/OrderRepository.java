package tr.com.bilkent.fods.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.order.Order;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT * FROM orders WHERE from_restaurant = ?1 AND placed_time IS NOT NULL " +
            "ORDER BY placed_time DESC", nativeQuery = true)
    Page<Order> getOrdersOfRestaurant(Restaurant restaurant, Pageable pageable);

    @Query(value = "SELECT * FROM orders WHERE belongs_to = ?1 AND placed_time IS NULL", nativeQuery = true)
    Order getBasket(String customer);

    @Query(value = "SELECT * FROM orders O WHERE O.status = 'WAITING_FOR_DELIVERER' AND " +
            "EXISTS (SELECT * FROM works_with W WHERE W.deliverer_username = ?1 AND " +
            "O.from_restaurant = W.restaurant_id)", nativeQuery = true)
    List<Order> getOrdersWaitingForDelivery(String deliverer);
}
