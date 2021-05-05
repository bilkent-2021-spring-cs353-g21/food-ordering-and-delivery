package tr.com.bilkent.fods.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.dto.restaurant.RestaurantDTO;
import tr.com.bilkent.fods.dto.restaurant.RestaurantNameDTO;
import tr.com.bilkent.fods.dto.user.EditUserDTO;
import tr.com.bilkent.fods.dto.user.UserDTO;
import tr.com.bilkent.fods.dto.user.UserWithoutPasswordDTO;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;
import tr.com.bilkent.fods.service.RestaurantService;
import tr.com.bilkent.fods.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/manager")
public class RestaurantManagerController {
    private final UserService userService;
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantManagerController(UserService userService,
                                       RestaurantService restaurantService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    @PostMapping("/register")
    public CustomHTTPResponse<Void> register(@RequestBody @Valid UserDTO manager) {
        log.info("Restaurant manager register request: {}", manager);
        userService.register(manager, RestaurantManager.class);
        return new CustomHTTPResponse<>("Registration successful.");
    }

    @ApiOperation("Get the account details of the logged-in manager.")
    @GetMapping("/account")
    public CustomHTTPResponse<UserWithoutPasswordDTO> getAccount(@ApiIgnore Authentication authentication) {
        log.info("Restaurant manager get account details request");

        String username = authentication.getName();
        UserWithoutPasswordDTO user = userService.getUserWithoutPassword(username);

        return new CustomHTTPResponse<>(user);
    }

    @ApiOperation("Edit the account of the logged-in manager.")
    @PatchMapping("/account")
    public CustomHTTPResponse<Void> editAccount(@RequestBody @Valid EditUserDTO user,
                                                @ApiIgnore Authentication authentication) {
        log.info("Restaurant manager edit account request");

        String username = authentication.getName();
        userService.edit(username, RestaurantManager.class, user);

        return new CustomHTTPResponse<>("Account successfully modified.");
    }

    @ApiOperation("Delete the account of the logged-in manager. NOTE: The managed restaurants are deleted as well.")
    @DeleteMapping("/account")
    public CustomHTTPResponse<Void> deleteAccount(@ApiIgnore Authentication authentication) {
        log.info("Restaurant manager delete account request");

        String username = authentication.getName();
        userService.delete(username, RestaurantManager.class);

        SecurityContextHolder.clearContext();
        return new CustomHTTPResponse<>("Account deleted.");
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
}
