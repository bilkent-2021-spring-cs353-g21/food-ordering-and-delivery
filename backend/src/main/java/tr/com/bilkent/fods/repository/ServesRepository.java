package tr.com.bilkent.fods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bilkent.fods.entity.serves.RestaurantDistrictKey;
import tr.com.bilkent.fods.entity.serves.Serves;

public interface ServesRepository extends JpaRepository<Serves, RestaurantDistrictKey> {
}
