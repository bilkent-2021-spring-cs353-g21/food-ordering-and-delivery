package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.district.District;
import tr.com.bilkent.fods.entity.district.DistrictKey;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import java.util.List;
import java.util.Set;

public interface DistrictRepository extends JpaRepository<District, DistrictKey> {
    @Query(value = "SELECT * FROM district WHERE (city_name, district_name) IN " +
            "(SELECT (city_name, district_name) FROM serves WHERE rid = ?1)", nativeQuery = true)
    List<District> getServedDistricts(Restaurant restaurant);

    @Query(value = "SELECT city_name FROM district ORDER BY city_name", nativeQuery = true)
    Set<String> getCityNames();

    @Query(value = "SELECT * FROM district WHERE city_name = ?1 ORDER BY district_name", nativeQuery = true)
    List<District> getByCityName(String cityName);
}
