package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.district.District;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT * FROM restaurant WHERE rid IN " +
            "(SELECT rid FROM serves " +
            "WHERE city_name = :#{#district.districtKey.cityName} AND " +
            "district_name = :#{#district.districtKey.districtName})", nativeQuery = true)
    List<Restaurant> getRestaurantsServingDistrict(District district);

    @Query(value = "SELECT * FROM restaurant WHERE manager_username = ?1", nativeQuery = true)
    List<Restaurant> getManagedRestaurants(RestaurantManager manager);
}
