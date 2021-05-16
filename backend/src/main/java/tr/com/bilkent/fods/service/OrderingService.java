package tr.com.bilkent.fods.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.bilkent.fods.dto.district.DistrictDTO;
import tr.com.bilkent.fods.dto.meal.AddToBasketDTO;
import tr.com.bilkent.fods.dto.meal.BasketContentDTO;
import tr.com.bilkent.fods.entity.deliveryaddress.DeliveryAddress;
import tr.com.bilkent.fods.entity.meal.Meal;
import tr.com.bilkent.fods.entity.meal.MealKey;
import tr.com.bilkent.fods.entity.order.Order;
import tr.com.bilkent.fods.entity.order.OrderStatus;
import tr.com.bilkent.fods.entity.ordercontent.OrderContent;
import tr.com.bilkent.fods.exception.*;
import tr.com.bilkent.fods.mapper.DistrictMapper;
import tr.com.bilkent.fods.mapper.MealMapper;
import tr.com.bilkent.fods.repository.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class OrderingService {
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private final OrderContentRepository orderContentRepository;
    private final MealRepository mealRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final UserService userService;

    @Autowired
    public OrderingService(RestaurantRepository restaurantRepository,
                           OrderRepository orderRepository,
                           OrderContentRepository orderContentRepository,
                           MealRepository mealRepository,
                           DeliveryAddressRepository deliveryAddressRepository,
                           UserService userService) {
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
        this.orderContentRepository = orderContentRepository;
        this.mealRepository = mealRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.userService = userService;
    }

    @Transactional
    public void addToBasket(String username, AddToBasketDTO dto) {
        Meal meal = mealRepository.findById(new MealKey(dto.getRid(), dto.getName())).orElseThrow(
                () -> new NonExistsMealException(dto.getRid(), dto.getName()));
        Order basket = orderRepository.getBasket(username);

        if (basket.getFromRestaurant() != null && basket.getFromRestaurant().getRid() != dto.getRid()) {
            throw new BasketContentMultipleRestaurantsException();
        }

        Integer maxIndex = orderContentRepository.getMaxInOrderIndex(basket);
        int newIndex = maxIndex == null ? 0 : maxIndex + 1;
        OrderContent basketContent = MealMapper.INSTANCE.addToBasketDtoToOrderContent(dto);
        basketContent.getOrderContentKey().setInOrderIndex(newIndex);
        basketContent.getOrderContentKey().setOid(basket.getOid());
        basketContent.setMealPrice(meal.getPrice());
        basketContent.setMeal(meal);
        basketContent.setOrder(basket);

        basket.setFromRestaurant(restaurantRepository.findById(dto.getRid()).orElseThrow(
                () -> new NonExistsRestaurantException(dto.getRid())));
        basket.setCost(basket.getCost() + basketContent.getMealPrice() * basketContent.getQuantity());

        orderRepository.save(basket);
        orderContentRepository.save(basketContent);
    }

    public List<BasketContentDTO> getBasket(String username) {
        Order basket = orderRepository.getBasket(username);
        List<OrderContent> basketContent = orderContentRepository.getContentsOfOrder(basket);
        return MealMapper.INSTANCE.orderContentsToBasketContentDto(basketContent);
    }

    @Transactional
    public void removeFromBasket(String username, int inOrderIndex) {
        Order basket = orderRepository.getBasket(username);
        int deleted = orderContentRepository.deleteByOidAndInOrderIndex(basket.getOid(), inOrderIndex);
        if (deleted == 0) {
            throw new InOrderIndexInvalidException(inOrderIndex);
        }
    }

    @Transactional
    public void activateDeliveryAddress(String username, DistrictDTO address) {
        int updated = deliveryAddressRepository
                .activateAddress(username, address.getCityName(), address.getDistrictName());
        if (updated == 0) {
            throw new NonExistsAddressException(address.getCityName(), address.getDistrictName());
        }
    }

    @Transactional
    public Long placeOrder(String username, Timestamp requestedDeliveryTime) {
        DeliveryAddress destination = deliveryAddressRepository.getActiveAddress(username)
                .orElseThrow(NoActiveAddressException::new);

        Order order = orderRepository.getBasket(username);
        if (order.getFromRestaurant() == null) {
            throw new BasketEmptyException();
        }
        double minDeliveryCost = restaurantRepository.getMinDeliveryCost(order.getFromRestaurant().getRid(),
                DistrictMapper.INSTANCE.districtToDto(destination.getDistrict()));
        if (order.getCost() < minDeliveryCost) {
            throw new UnderMinDeliveryCostException(order.getCost(), minDeliveryCost);
        }

        order.setPlacedTime(Timestamp.from(Instant.now()));
        order.setStatus(OrderStatus.WAITING);
        order.setRequestedDeliveryTime(requestedDeliveryTime);
        order.setDestination(destination);
        order = orderRepository.save(order);

        userService.createCustomerBasket(username);
        return order.getOid();
    }
}
