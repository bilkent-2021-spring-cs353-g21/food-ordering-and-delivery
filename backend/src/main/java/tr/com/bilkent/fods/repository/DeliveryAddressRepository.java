package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.deliveryaddress.DeliveryAddress;
import tr.com.bilkent.fods.entity.deliveryaddress.DeliveryAddressKey;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, DeliveryAddressKey> {
    @Query(value = "SELECT * FROM delivery_address WHERE username = ?1", nativeQuery = true)
    List<DeliveryAddress> findAddressesOfCustomer(Customer customer);
}
