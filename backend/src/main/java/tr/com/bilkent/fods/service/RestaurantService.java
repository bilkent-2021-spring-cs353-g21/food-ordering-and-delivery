package tr.com.bilkent.fods.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bilkent.fods.dto.restaurant.RestaurantDTO;
import tr.com.bilkent.fods.dto.restaurant.RestaurantNameDTO;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;
import tr.com.bilkent.fods.exception.NonExistsRestaurantException;
import tr.com.bilkent.fods.exception.RestaurantNotManagedException;
import tr.com.bilkent.fods.mapper.RestaurantMapper;
import tr.com.bilkent.fods.repository.RestaurantRepository;

import java.util.List;

@Slf4j
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,
                             UserService userService) {
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }

    /**
     * Create a restaurant, and set its manager to the manager with the given username.
     */
    public RestaurantDTO create(RestaurantDTO restaurantDto, String username) {
        restaurantDto.setRid(null);

        Restaurant restaurant = RestaurantMapper.INSTANCE.dtoToRestaurant(restaurantDto);

        RestaurantManager manager = (RestaurantManager) userService.getUser(username);
        restaurant.setManager(manager);

        restaurant = restaurantRepository.save(restaurant);
        log.info("Created restaurant {}", restaurant);
        return RestaurantMapper.INSTANCE.restaurantToDto(restaurant);
    }

    public List<RestaurantNameDTO> getManagedRestaurants(String username) {
        RestaurantManager manager = (RestaurantManager) userService.getUser(username);
        List<Restaurant> restaurants = restaurantRepository.getManagedRestaurants(manager);

        return RestaurantMapper.INSTANCE.restaurantsToNameDtos(restaurants);
    }

    public RestaurantDTO getRestaurant(long rid, String username) {
        Restaurant restaurant = getManagedRestaurant(rid, username);
        return RestaurantMapper.INSTANCE.restaurantToDto(restaurant);
    }

    public RestaurantDTO editRestaurant(long rid, RestaurantDTO newData, String username) {
        newData.setRid(null);

        Restaurant restaurant = getManagedRestaurant(rid, username);
        RestaurantMapper.INSTANCE.updateRestaurantFromDto(newData, restaurant);
        restaurant = restaurantRepository.save(restaurant);

        return RestaurantMapper.INSTANCE.restaurantToDto(restaurant);
    }

    public void deleteRestaurant(long rid, String username) {
        Restaurant restaurant = getManagedRestaurant(rid, username);
        restaurantRepository.delete(restaurant);
    }

    private Restaurant getManagedRestaurant(long rid, String username) {
        Restaurant restaurant = restaurantRepository.findById(rid).orElseThrow(
                () -> new NonExistsRestaurantException(rid));

        boolean isRestaurantManaged = username.equals(restaurant.getManager().getUsername());
        if (!isRestaurantManaged) {
            throw new RestaurantNotManagedException(rid);
        }

        return restaurant;
    }
}
