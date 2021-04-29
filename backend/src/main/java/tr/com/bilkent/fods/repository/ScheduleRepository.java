package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;
import tr.com.bilkent.fods.entity.schedule.Schedule;
import tr.com.bilkent.fods.entity.schedule.ScheduleKey;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleKey> {
    @Query(value = "SELECT * FROM schedule WHERE rid = ?1", nativeQuery = true)
    List<Schedule> getByRestaurant(Restaurant restaurant);
}
