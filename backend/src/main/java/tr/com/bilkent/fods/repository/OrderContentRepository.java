package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.order.Order;
import tr.com.bilkent.fods.entity.ordercontent.OrderContent;
import tr.com.bilkent.fods.entity.ordercontent.OrderContentKey;

import java.util.List;

public interface OrderContentRepository extends JpaRepository<OrderContent, OrderContentKey> {
    String SQL_CONTENTS_OF_ORDER = "FROM order_content WHERE oid = ?1";

    @Query(value = "SELECT * " + SQL_CONTENTS_OF_ORDER, nativeQuery = true)
    List<OrderContent> getContentsOfOrder(Order order);

    @Query(value = "SELECT * " + SQL_CONTENTS_OF_ORDER, nativeQuery = true)
    List<OrderContent> getContentsOfOrder(long oid);

    @Query(value = "SELECT COUNT(*) " + SQL_CONTENTS_OF_ORDER, nativeQuery = true)
    Integer countContentsOfOrder(Order oid);
}
