package tr.com.bilkent.fods.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.bilkent.fods.dto.meal.AddToBasketDTO;
import tr.com.bilkent.fods.entity.meal.Meal;
import tr.com.bilkent.fods.entity.meal.MealKey;
import tr.com.bilkent.fods.entity.order.Order;
import tr.com.bilkent.fods.entity.ordercontent.OrderContent;
import tr.com.bilkent.fods.exception.BasketContentMultipleRestaurantsException;
import tr.com.bilkent.fods.exception.NonExistsMealException;
import tr.com.bilkent.fods.exception.NonExistsRestaurantException;
import tr.com.bilkent.fods.mapper.MealMapper;
import tr.com.bilkent.fods.repository.MealRepository;
import tr.com.bilkent.fods.repository.OrderContentRepository;
import tr.com.bilkent.fods.repository.OrderRepository;
import tr.com.bilkent.fods.repository.RestaurantRepository;

@Slf4j
@Service
public class OrderingService {
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private final OrderContentRepository orderContentRepository;
    private final MealRepository mealRepository;

    @Autowired
    public OrderingService(RestaurantRepository restaurantRepository,
                           OrderRepository orderRepository,
                           OrderContentRepository orderContentRepository,
                           MealRepository mealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
        this.orderContentRepository = orderContentRepository;
        this.mealRepository = mealRepository;
    }

    @Transactional
    public void addToBasket(String username, AddToBasketDTO dto) {
        Meal meal = mealRepository.findById(new MealKey(dto.getRid(), dto.getName())).orElseThrow(
                () -> new NonExistsMealException(dto.getRid(), dto.getName()));
        Order basket = orderRepository.getBasket(username);

        if (basket.getFromRestaurant() != null && basket.getFromRestaurant().getRid() != dto.getRid()) {
            throw new BasketContentMultipleRestaurantsException();
        }

        int newIndex = orderContentRepository.countContentsOfOrder(basket) + 1;
        OrderContent basketContent = MealMapper.INSTANCE.addToBasketDtoToOrderContent(dto);
        basketContent.getOrderContentKey().setInOrderIndex(newIndex);
        basketContent.getOrderContentKey().setOid(basket.getOid());
        basketContent.setMealPrice(meal.getPrice());

        basket.setFromRestaurant(restaurantRepository.findById(dto.getRid()).orElseThrow(
                () -> new NonExistsRestaurantException(dto.getRid())));

        orderRepository.save(basket);
        orderContentRepository.save(basketContent);
    }
}
