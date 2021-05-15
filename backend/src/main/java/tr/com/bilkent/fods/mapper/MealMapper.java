package tr.com.bilkent.fods.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import tr.com.bilkent.fods.config.MapperConfig;
import tr.com.bilkent.fods.dto.meal.AddToBasketDTO;
import tr.com.bilkent.fods.dto.meal.MealDTO;
import tr.com.bilkent.fods.dto.search.MealResultDTO;
import tr.com.bilkent.fods.entity.meal.Meal;
import tr.com.bilkent.fods.entity.ordercontent.OrderContent;

import java.util.List;

@Mapper(config = MapperConfig.class, uses = OrderMapper.class)
public interface MealMapper {
    MealMapper INSTANCE = Mappers.getMapper(MealMapper.class);

    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "mealKey", source = ".")
    Meal mealDtoToMeal(MealDTO meal);

    @InheritInverseConfiguration(name = "mealDtoToMeal")
    MealDTO mealToMealDto(Meal meal);

    List<MealDTO> mealsToMealDtos(List<Meal> meals);

    @Mapping(target = "rid", source = "mealKey.rid")
    @Mapping(target = "mealName", source = "mealKey.name")
    @Mapping(target = "restaurantName", source = "restaurant.name")
    MealResultDTO mealsToMealResult(Meal meal);

    List<MealResultDTO> mealsToMealResult(List<Meal> meals);

    @InheritConfiguration(name = "mealDtoToMeal")
    void updateMealFromDto(MealDTO mealDto, @MappingTarget Meal meal);

    @Mapping(target = "orderContentKey", source = ".")
    @Mapping(target = "orderContentKey.mealKey", source = ".")
    @Mapping(target = "mealPrice", ignore = true)
    @Mapping(target = "meal", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderContent addToBasketDtoToOrderContent(AddToBasketDTO meal);
}
