package tr.com.bilkent.fods.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tr.com.bilkent.fods.config.MapperConfig;
import tr.com.bilkent.fods.dto.meal.MealDTO;
import tr.com.bilkent.fods.dto.search.MealResultDTO;
import tr.com.bilkent.fods.entity.meal.Meal;

import java.util.List;

@Mapper(config = MapperConfig.class, uses = OrderMapper.class)
public interface MealMapper {
    MealMapper INSTANCE = Mappers.getMapper(MealMapper.class);

    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "mealKey", source = ".")
    Meal mealDtoToMeal(MealDTO meal);

    @InheritInverseConfiguration
    MealDTO mealToMealDto(Meal meal);

    List<MealDTO> mealsToMealDtos(List<Meal> meals);

    @Mapping(target = "rid", source = "mealKey.rid")
    @Mapping(target = "mealName", source = "mealKey.name")
    @Mapping(target = "restaurantName", source = "restaurant.name")
    MealResultDTO mealsToMealResult(Meal meal);

    List<MealResultDTO> mealsToMealResult(List<Meal> meals);
}
