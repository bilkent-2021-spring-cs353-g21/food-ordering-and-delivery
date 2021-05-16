package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.dto.district.DistrictDTO;
import tr.com.bilkent.fods.dto.search.SearchDTO;
import tr.com.bilkent.fods.entity.district.District;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT * FROM restaurant WHERE deleted = 0 AND rid IN " +
            "(SELECT rid FROM serves " +
            "WHERE city_name = :#{#district.districtKey.cityName} AND " +
            "district_name = :#{#district.districtKey.districtName})", nativeQuery = true)
    List<Restaurant> getRestaurantsServingDistrict(District district);

    @Query(value = "SELECT * FROM restaurant WHERE deleted = 0 AND manager_username = ?1", nativeQuery = true)
    List<Restaurant> getManagedRestaurants(RestaurantManager manager);

    String SQL_R_SERVES_DISTRICT = "EXISTS (SELECT * FROM serves S WHERE S.rid = R.rid " +
            "AND S.city_name = :#{#search.district.cityName} " +
            "AND S.district_name = :#{#search.district.districtName} " +
            "AND (:#{#search.maxMinDeliveryCost} IS NULL OR S.min_amount <= :#{#search.maxMinDeliveryCost}))";

    String SQL_R_SCORE = "SELECT IFNULL(AVG(C.restaurant_score), 0) FROM orders O " +
            "INNER JOIN comment C ON O.oid = C.oid WHERE O.from_restaurant = ";

    @Query(value = "SELECT R.* FROM restaurant R WHERE deleted = 0 AND" +
            "(IFNULL(:#{#search.onlyServingDistrict}, 0) = 0 OR " + SQL_R_SERVES_DISTRICT + ") " +
            "AND (:#{#search.scoreAtLeast} IS NULL OR (" + SQL_R_SCORE + "R.rid) >= :#{#search.scoreAtLeast} )" +
            "AND (LOWER(R.name) LIKE LOWER(CONCAT('%', :#{#search.keyword}, '%')) " +
            "OR LOWER(R.description) LIKE LOWER(CONCAT('%', :#{#search.keyword}, '%')))", nativeQuery = true)
    List<Restaurant> search(SearchDTO search);

    @Query(value = SQL_R_SCORE + "?1", nativeQuery = true)
    double getRestaurantScore(Long rid);

    @Query(value = "SELECT S.min_amount FROM serves S WHERE S.rid = ?1 " +
            "AND S.city_name = :#{#district.cityName} " +
            "AND S.district_name = :#{#district.districtName}", nativeQuery = true)
    double getMinDeliveryCost(Long rid, DistrictDTO district);

    @Modifying
    @Query(value = "UPDATE restaurant SET deleted = 1 WHERE rid = ?1", nativeQuery = true)
    void delete(Restaurant restaurant);
}
