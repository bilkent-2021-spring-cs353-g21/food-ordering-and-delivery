package tr.com.bilkent.fods.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.dto.search.SearchDTO;
import tr.com.bilkent.fods.entity.meal.Meal;
import tr.com.bilkent.fods.entity.meal.MealKey;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, MealKey> {
    @Query(value = "SELECT * FROM meal WHERE deleted = 0 AND rid = ?1 ORDER BY type", nativeQuery = true)
    List<Meal> getMenu(Restaurant restaurant);

    String SQL_R_SERVES_DISTRICT = "EXISTS (SELECT * FROM serves S WHERE S.rid = R.rid " +
            "AND S.city_name = :#{#search.district.cityName} " +
            "AND S.district_name = :#{#search.district.districtName} " +
            "AND (:#{#search.maxMinDeliveryCost} IS NULL OR S.min_amount <= :#{#search.maxMinDeliveryCost}))";

    String SQL_R_SCORE = "SELECT IFNULL(AVG(C.restaurant_score), 0) FROM orders O " +
            "INNER JOIN comment C ON O.oid = C.oid WHERE O.from_restaurant = ";

    @Query(value = "SELECT M.* FROM meal M INNER JOIN restaurant R ON M.rid = R.rid WHERE M.deleted = 0 AND" +
            "(IFNULL(:#{#search.onlyServingDistrict}, 0) = 0 OR " + SQL_R_SERVES_DISTRICT + ") " +
            "AND (:#{#search.scoreAtLeast} IS NULL OR (" + SQL_R_SCORE + "R.rid) >= :#{#search.scoreAtLeast} )" +
            "AND (LOWER(M.name) LIKE LOWER(CONCAT('%', :#{#search.keyword}, '%')) " +
            "   OR LOWER(M.description) LIKE LOWER(CONCAT('%', :#{#search.keyword}, '%')) " +
            "AND M.price BETWEEN IFNULL(:#{#search.minPrice}, 0) AND IFNULL(:#{#search.maxPrice}, 1000)) ", nativeQuery = true)
    Page<Meal> search(SearchDTO search, Pageable pageable);

    @Override
    @Modifying
    @Query(value = "UPDATE meal SET deleted = 1 WHERE rid = :#{#mealKey.rid} AND name = :#{#mealKey.name}",
            nativeQuery = true)
    void deleteById(MealKey mealKey);
}
