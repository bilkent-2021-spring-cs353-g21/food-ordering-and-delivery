package tr.com.bilkent.fods.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.dto.user.RestaurantManagerDTO;
import tr.com.bilkent.fods.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/manager")
public class RestaurantManagerController {
    private final UserService userService;

    @Autowired
    public RestaurantManagerController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public CustomHTTPResponse register(@RequestBody @Valid RestaurantManagerDTO manager) {
        log.debug("Restaurant manager register request: {}", manager);
        userService.register(manager);
        return new CustomHTTPResponse("success");
    }
}
