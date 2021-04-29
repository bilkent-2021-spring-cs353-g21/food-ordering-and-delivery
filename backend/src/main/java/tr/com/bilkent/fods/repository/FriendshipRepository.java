package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.friendship.Friendship;
import tr.com.bilkent.fods.entity.friendship.FriendshipKey;

public interface FriendshipRepository extends JpaRepository<Friendship, FriendshipKey> {
    @Query(value = "(SELECT * FROM friend WHERE customer_username = ?1 AND friend_username = ?2) " +
            "UNION (SELECT * FROM friend WHERE customer_username = ?2 AND friend_username = ?1)", nativeQuery = true)
    Friendship getFriendshipOfCustomers(Customer customer, Customer friend);
}
