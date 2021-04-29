package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.district.District;
import tr.com.bilkent.fods.entity.district.DistrictKey;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, DistrictKey> {
    @Query(value = "SELECT * FROM district WHERE (city_name, district_name) IN " +
            "(SELECT (city_name, district_name) FROM serves WHERE rid = ?1)", nativeQuery = true)
    List<District> getServedDistricts(Restaurant restaurant);
}
