package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.customer.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(value = "SELECT * FROM customer WHERE username IN " +
            "((SELECT friend_username FROM friend WHERE customer_username = ?1) " +
            "UNION (SELECT customer_username FROM friend WHERE friend_username = ?1))", nativeQuery = true)
    List<Customer> findFriendsOfCustomer(Customer customer);
}
