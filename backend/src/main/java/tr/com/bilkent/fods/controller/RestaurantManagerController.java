package tr.com.bilkent.fods.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tr.com.bilkent.fods.dto.comment.CommentListDTO;
import tr.com.bilkent.fods.dto.meal.MealDTO;
import tr.com.bilkent.fods.dto.order.OrderListDTO;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.dto.restaurant.RestaurantDTO;
import tr.com.bilkent.fods.dto.restaurant.RestaurantNameDTO;
import tr.com.bilkent.fods.service.RestaurantService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/manager")
public class RestaurantManagerController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantManagerController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @ApiOperation("Retrieve the names and IDs of the restaurant managed by the logged-in user.")
    @GetMapping("/restaurants")
    public CustomHTTPResponse<List<RestaurantNameDTO>> getManagedRestaurants(@ApiIgnore Authentication authentication) {
        log.info("Get managed restaurants request");

        String username = authentication.getName();
        List<RestaurantNameDTO> restaurants = restaurantService.getManagedRestaurants(username);

        return new CustomHTTPResponse<>(restaurants);
    }

    @ApiOperation("Create a new restaurant.")
    @PutMapping("/restaurant")
    public CustomHTTPResponse<RestaurantDTO> createRestaurant(@RequestBody @Valid RestaurantDTO restaurant,
                                                              @ApiIgnore Authentication authentication) {
        log.info("Create restaurant request: {}", restaurant);

        String username = authentication.getName();
        RestaurantDTO createdRestaurant = restaurantService.create(restaurant, username);

        return new CustomHTTPResponse<>(createdRestaurant);
    }

    @ApiOperation("Get restaurant details.")
    @GetMapping("/restaurant/{rid}")
    public CustomHTTPResponse<RestaurantDTO> getRestaurant(@PathVariable("rid") long rid,
                                                           @ApiIgnore Authentication authentication) {
        log.info("Get restaurant request: rid = {}", rid);

        String username = authentication.getName();
        RestaurantDTO restaurant = restaurantService.getRestaurant(rid, username);
        return new CustomHTTPResponse<>(restaurant);
    }

    @ApiOperation("Edit restaurant.")
    @PatchMapping("/restaurant/{rid}")
    public CustomHTTPResponse<RestaurantDTO> editRestaurant(@PathVariable("rid") long rid,
                                                            @RequestBody @Valid RestaurantDTO restaurant,
                                                            @ApiIgnore Authentication authentication) {
        log.info("Edit restaurant request: rid = {} restaurant: {}", rid, restaurant);

        String username = authentication.getName();
        RestaurantDTO modifiedRestaurant = restaurantService.editRestaurant(rid, restaurant, username);
        return new CustomHTTPResponse<>(modifiedRestaurant, "Restaurant successfully modified.");
    }

    @ApiOperation("Delete restaurant.")
    @DeleteMapping("/restaurant/{rid}")
    public CustomHTTPResponse<Void> deleteRestaurant(@PathVariable("rid") long rid,
                                                     @ApiIgnore Authentication authentication) {
        log.info("Delete restaurant request: rid = {}", rid);

        String username = authentication.getName();
        restaurantService.deleteRestaurant(rid, username);
        return new CustomHTTPResponse<>("Restaurant deleted.");
    }

    @ApiOperation("Get orders of restaurant sorted by the placed date.")
    @GetMapping("/restaurant/{rid}/orders")
    public CustomHTTPResponse<OrderListDTO> getOrdersOfRestaurant(@PathVariable("rid") long rid,
                                                                  @RequestParam(value = "page", defaultValue = "0")
                                                                  @Min(0) int page,
                                                                  @RequestParam(value = "limit", defaultValue = "25")
                                                                  @Min(1) int limit,
                                                                  @ApiIgnore Authentication authentication) {
        log.info("Get orders request: rid = {} page = {} limit = {}", rid, page, limit);

        String username = authentication.getName();
        return new CustomHTTPResponse<>(restaurantService.getOrders(rid, username, page, limit));
    }

    @ApiOperation("Get comments of restaurant sorted by the placed date.")
    @GetMapping("/restaurant/{rid}/comments")
    public CustomHTTPResponse<CommentListDTO> getCommentsOfRestaurant(@PathVariable("rid") long rid,
                                                                      @RequestParam(value = "page", defaultValue = "0")
                                                                      @Min(0) int page,
                                                                      @RequestParam(value = "limit", defaultValue = "25")
                                                                      @Min(1) int limit,
                                                                      @ApiIgnore Authentication authentication) {
        log.info("Get comments request: rid = {} page = {} limit = {}", rid, page, limit);

        String username = authentication.getName();
        return new CustomHTTPResponse<>(restaurantService.getComments(rid, username, page, limit));
    }

    @ApiOperation("Reply to a comment.")
    @PutMapping("/reply_to_comment/{oid}")
    public CustomHTTPResponse<Void> replyToComment(@PathVariable("oid") long oid,
                                                   @RequestBody @Length(min = 1) String response,
                                                   @ApiIgnore Authentication authentication) {
        log.info("Reply to a comment request: oid = {} response = {}", oid, response);

        String username = authentication.getName();
        restaurantService.replyToComment(username, oid, response);
        return new CustomHTTPResponse<>("Reply successful.");
    }

    @ApiOperation("Get meals of the restaurant sorted by type.")
    @GetMapping("/restaurant/{rid}/meals")
    public CustomHTTPResponse<List<MealDTO>> getMeals(@PathVariable("rid") long rid,
                                                      @ApiIgnore Authentication authentication) {
        log.info("Get meals request: rid = {}", rid);

        String username = authentication.getName();
        return new CustomHTTPResponse<>(restaurantService.getMeals(rid, username));
    }

    @ApiOperation("Create a meal in the restaurant.")
    @PutMapping("/restaurant/{rid}/meals")
    public CustomHTTPResponse<CommentListDTO> createMeal(@PathVariable("rid") long rid,
                                                         @RequestBody @Valid MealDTO meal,
                                                         @ApiIgnore Authentication authentication) {
        log.info("Create meal request: rid = {} meal = {}", rid, meal);

        String username = authentication.getName();
        restaurantService.createMeal(rid, username, meal);
        return new CustomHTTPResponse<>("Meal successfully added to the system.");
    }
}
