package tr.com.bilkent.fods.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import tr.com.bilkent.fods.config.MapperConfig;
import tr.com.bilkent.fods.dto.restaurant.RestaurantDTO;
import tr.com.bilkent.fods.dto.restaurant.RestaurantNameDTO;
import tr.com.bilkent.fods.dto.search.RestaurantResultDTO;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "address.districtKey", source = "address")
    Restaurant dtoToRestaurant(RestaurantDTO restaurant);

    @InheritInverseConfiguration(name = "dtoToRestaurant")
    RestaurantDTO restaurantToDto(Restaurant restaurant);

    List<RestaurantNameDTO> restaurantsToNameDtos(List<Restaurant> restaurants);

    @Mapping(target = "manager", ignore = true)
    void updateRestaurantFromDto(RestaurantDTO restaurantDto, @MappingTarget Restaurant restaurant);

    List<RestaurantResultDTO> restaurantsToRestaurantResultDtos(List<Restaurant> restaurants);
}
