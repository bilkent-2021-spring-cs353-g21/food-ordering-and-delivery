package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.deliveryaddress.DeliveryAddress;
import tr.com.bilkent.fods.entity.deliveryaddress.DeliveryAddressKey;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, DeliveryAddressKey> {
    @Query(value = "SELECT * FROM delivery_address WHERE username = ?1", nativeQuery = true)
    List<DeliveryAddress> findAddressesOfCustomer(Customer customer);

    @Modifying
    @Query(value = "UPDATE delivery_address SET active = 1 WHERE " +
            "username = ?1 AND city_name = ?2 AND district_name = ?3", nativeQuery = true)
    int activateAddress(String username, String cityName, String districtName);
}
