package tr.com.bilkent.fods.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tr.com.bilkent.fods.dto.district.DistrictDTO;
import tr.com.bilkent.fods.dto.meal.AddToBasketDTO;
import tr.com.bilkent.fods.dto.meal.BasketContentDTO;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.service.OrderingService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final OrderingService orderingService;

    @Autowired
    public CustomerController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    @ApiOperation("Get basket.")
    @GetMapping("/basket")
    public CustomHTTPResponse<List<BasketContentDTO>> getBasket(@ApiIgnore Authentication authentication) {
        log.info("Get basket request");

        String username = authentication.getName();
        return new CustomHTTPResponse<>(orderingService.getBasket(username));
    }

    @ApiOperation("Add to basket.")
    @PutMapping("/basket")
    public CustomHTTPResponse<Void> addToBasket(@RequestBody @Valid AddToBasketDTO meal,
                                                @ApiIgnore Authentication authentication) {
        log.info("Add to basket request: {}", meal);

        String username = authentication.getName();
        orderingService.addToBasket(username, meal);
        return new CustomHTTPResponse<>("Added to basket");
    }

    @ApiOperation("Remove from basket.")
    @DeleteMapping("/basket/{in_order_index}")
    public CustomHTTPResponse<Void> removeFromBasket(@PathVariable("in_order_index") int inOrderIndex,
                                                     @ApiIgnore Authentication authentication) {
        log.info("Remove from basket request: index = {}", inOrderIndex);

        String username = authentication.getName();
        orderingService.removeFromBasket(username, inOrderIndex);
        return new CustomHTTPResponse<>("Removed from basket");
    }

    @ApiOperation("Activate delivery address.")
    @PostMapping("/address")
    public CustomHTTPResponse<Void> removeFromBasket(@RequestBody DistrictDTO address,
                                                     @ApiIgnore Authentication authentication) {
        log.info("Activate delivery address request: {}", address);

        String username = authentication.getName();
        orderingService.activateDeliveryAddress(username, address);
        return new CustomHTTPResponse<>("Address activated");
    }

    @ApiOperation("Place an order using the current basket and active address. Returns order id in data field.")
    @PostMapping("/order")
    public CustomHTTPResponse<Long> placeOrder(@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
                                               @RequestBody Timestamp requestedDeliveryTime,
                                               @ApiIgnore Authentication authentication) {
        log.info("Place order request: {}", requestedDeliveryTime);

        String username = authentication.getName();
        Long oid = orderingService.placeOrder(username, requestedDeliveryTime);
        return new CustomHTTPResponse<>(oid, "Order placed.");
    }
}
