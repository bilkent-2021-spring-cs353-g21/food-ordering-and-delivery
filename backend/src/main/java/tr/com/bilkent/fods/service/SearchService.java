package tr.com.bilkent.fods.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tr.com.bilkent.fods.dto.search.MealSearchResultsDTO;
import tr.com.bilkent.fods.dto.search.RestaurantResultDTO;
import tr.com.bilkent.fods.dto.search.SearchDTO;
import tr.com.bilkent.fods.dto.search.SearchResultDTO;
import tr.com.bilkent.fods.entity.meal.Meal;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;
import tr.com.bilkent.fods.mapper.MealMapper;
import tr.com.bilkent.fods.mapper.RestaurantMapper;
import tr.com.bilkent.fods.repository.MealRepository;
import tr.com.bilkent.fods.repository.RestaurantRepository;

import java.util.List;

@Slf4j
@Service
public class SearchService {
    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;

    @Autowired
    public SearchService(RestaurantRepository restaurantRepository,
                         MealRepository mealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }

    public SearchResultDTO search(SearchDTO search, int page, int limit) {
        List<Restaurant> restaurants = restaurantRepository.search(search);
        List<RestaurantResultDTO> restaurantResult = RestaurantMapper.INSTANCE
                .restaurantsToRestaurantResultDtos(restaurants);
        restaurantResult.forEach(restaurant -> {
            restaurant.setScore(restaurantRepository.getRestaurantScore(restaurant.getRid()));
            restaurant.setMinDeliveryCost(
                    restaurantRepository.getMinDeliveryCost(restaurant.getRid(), search.getDistrict()));
        });

        Page<Meal> meals = mealRepository.search(search, PageRequest.of(page, limit));
        MealSearchResultsDTO mealSearchResults = new MealSearchResultsDTO(meals.getTotalElements(),
                meals.getTotalPages(), MealMapper.INSTANCE.mealsToMealResult(meals.getContent()));

        SearchResultDTO searchResult = new SearchResultDTO(restaurantResult, mealSearchResults);
        log.warn("{}", searchResult);
        return searchResult;
    }
}
