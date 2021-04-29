package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bilkent.fods.entity.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
