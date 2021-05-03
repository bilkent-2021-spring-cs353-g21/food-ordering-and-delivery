package tr.com.bilkent.fods.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.dto.user.UserDTO;
import tr.com.bilkent.fods.entity.deliverer.Deliverer;
import tr.com.bilkent.fods.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/deliverer")
public class DelivererController {
    private final UserService userService;

    @Autowired
    public DelivererController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public CustomHTTPResponse<Void> register(@RequestBody @Valid UserDTO deliverer) {
        log.info("Deliverer register request: {}", deliverer);
        userService.register(deliverer, Deliverer.class);
        return new CustomHTTPResponse<>("success");
    }
}
