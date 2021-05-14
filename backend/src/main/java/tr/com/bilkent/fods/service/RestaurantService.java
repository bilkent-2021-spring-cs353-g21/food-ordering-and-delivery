package tr.com.bilkent.fods.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tr.com.bilkent.fods.dto.comment.CommentDTO;
import tr.com.bilkent.fods.dto.comment.CommentListDTO;
import tr.com.bilkent.fods.dto.meal.MealDTO;
import tr.com.bilkent.fods.dto.order.OrderDTO;
import tr.com.bilkent.fods.dto.order.OrderListDTO;
import tr.com.bilkent.fods.dto.restaurant.RestaurantDTO;
import tr.com.bilkent.fods.dto.restaurant.RestaurantNameDTO;
import tr.com.bilkent.fods.entity.comment.Comment;
import tr.com.bilkent.fods.entity.meal.Meal;
import tr.com.bilkent.fods.entity.meal.MealKey;
import tr.com.bilkent.fods.entity.order.Order;
import tr.com.bilkent.fods.entity.ordercontent.OrderContent;
import tr.com.bilkent.fods.entity.restaurant.Restaurant;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;
import tr.com.bilkent.fods.exception.*;
import tr.com.bilkent.fods.mapper.CommentMapper;
import tr.com.bilkent.fods.mapper.MealMapper;
import tr.com.bilkent.fods.mapper.OrderMapper;
import tr.com.bilkent.fods.mapper.RestaurantMapper;
import tr.com.bilkent.fods.repository.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Slf4j
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private final OrderContentRepository orderContentRepository;
    private final CommentRepository commentRepository;
    private final MealRepository mealRepository;
    private final UserService userService;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository,
                             OrderRepository orderRepository,
                             OrderContentRepository orderContentRepository,
                             CommentRepository commentRepository,
                             MealRepository mealRepository,
                             UserService userService) {
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
        this.orderContentRepository = orderContentRepository;
        this.commentRepository = commentRepository;
        this.mealRepository = mealRepository;
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

    public OrderListDTO getOrders(long rid, String username, int pageNo, int limit) {
        Restaurant restaurant = getManagedRestaurant(rid, username);
        Page<Order> orders = orderRepository.getOrdersOfRestaurant(restaurant, PageRequest.of(pageNo, limit));

        List<OrderDTO> orderDtos = OrderMapper.INSTANCE.orderDtosFromOrders(orders.getContent());
        orderDtos.forEach(orderDto -> {
            List<OrderContent> orderContents = orderContentRepository.getContentsOfOrder(orderDto.getOid());
            orderDto.setContent(OrderMapper.INSTANCE.orderContentDtosFromOrderContents(orderContents));
        });

        return new OrderListDTO(orders.getTotalElements(), orders.getTotalPages(), orderDtos);
    }

    public CommentListDTO getComments(long rid, String username, int page, int limit) {
        Restaurant restaurant = getManagedRestaurant(rid, username);
        Page<Comment> comments = commentRepository.getCommentsOfRestaurant(restaurant, PageRequest.of(page, limit));

        List<CommentDTO> commentDtos = CommentMapper.INSTANCE.commentDtosFromComment(comments.getContent());
        return new CommentListDTO(comments.getTotalElements(), comments.getTotalPages(), commentDtos);
    }

    public void replyToComment(String username, long oid, String response) {
        Comment comment = commentRepository.findById(oid).orElseThrow(
                () -> new NonExistsCommentException(oid));

        if (!username.equals(comment.getOrder().getFromRestaurant().getManager().getUsername())) {
            throw new RestaurantNotManagedException(comment.getOrder().getFromRestaurant().getRid());
        }

        if (comment.getResponse() != null) {
            throw new CommentAlreadyRepliedException(oid);
        }
        comment.setResponse(response);
        comment.setResponseTimestamp(Timestamp.from(Instant.now()));
        commentRepository.save(comment);
    }

    public List<MealDTO> getMeals(long rid, String username) {
        Restaurant restaurant = getManagedRestaurant(rid, username);
        List<Meal> meals = mealRepository.getMenu(restaurant);
        return MealMapper.INSTANCE.mealDtosFromMeals(meals);
    }

    public void createMeal(long rid, String username, MealDTO mealDto) {
        Restaurant restaurant = getManagedRestaurant(rid, username);

        MealKey mealKey = new MealKey(rid, mealDto.getName());
        mealRepository.findById(mealKey).ifPresent(meal -> {
            throw new MealExistsException(rid, mealDto.getName());
        });

        Meal meal = MealMapper.INSTANCE.mealFromDto(mealDto);
        meal.setMealKey(mealKey);
        meal.setRestaurant(restaurant);

        mealRepository.save(meal);
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
