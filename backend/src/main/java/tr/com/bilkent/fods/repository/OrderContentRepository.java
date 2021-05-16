package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.order.Order;
import tr.com.bilkent.fods.entity.ordercontent.OrderContent;
import tr.com.bilkent.fods.entity.ordercontent.OrderContentKey;

import java.util.List;

public interface OrderContentRepository extends JpaRepository<OrderContent, OrderContentKey> {
    String SQL_CONTENTS_OF_ORDER = "FROM order_content WHERE oid = ?1 ORDER BY in_order_index";

    @Query(value = "SELECT * " + SQL_CONTENTS_OF_ORDER, nativeQuery = true)
    List<OrderContent> getContentsOfOrder(Order order);

    @Query(value = "SELECT * " + SQL_CONTENTS_OF_ORDER, nativeQuery = true)
    List<OrderContent> getContentsOfOrder(long oid);

    @Query(value = "SELECT COUNT(*) " + SQL_CONTENTS_OF_ORDER, nativeQuery = true)
    Integer countContentsOfOrder(Order oid);

    @Query(value = "SELECT MAX(in_order_index) " + SQL_CONTENTS_OF_ORDER, nativeQuery = true)
    Integer getMaxInOrderIndex(Order oid);

    @Modifying
    @Query(value = "DELETE FROM order_content WHERE oid = ?1 AND in_order_index = ?2", nativeQuery = true)
    int deleteByOidAndInOrderIndex(long oid, int inOrderIndex);
}
