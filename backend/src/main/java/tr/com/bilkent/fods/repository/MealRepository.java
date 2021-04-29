package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.meal.Meal;
import tr.com.bilkent.fods.entity.meal.MealKey;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, MealKey> {
    @Query(value = "SELECT * FROM meal WHERE rid = ?1", nativeQuery = true)
    List<Meal> getMenu(Restaurant restaurant);
}
