package tr.com.bilkent.fods.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import tr.com.bilkent.fods.dto.meal.AddToBasketDTO;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.service.OrderingService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final OrderingService orderingService;

    @Autowired
    public CustomerController(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    @ApiOperation("Add to basket.")
    @PutMapping("/basket")
    public CustomHTTPResponse<Void> addToBasket(@RequestBody @Valid AddToBasketDTO meal,
                                                @ApiIgnore Authentication authentication) {
        log.info("Add to basket: {}", meal);

        String username = authentication.getName();
        orderingService.addToBasket(username, meal);
        return new CustomHTTPResponse<>("Added to basket");
    }
}
