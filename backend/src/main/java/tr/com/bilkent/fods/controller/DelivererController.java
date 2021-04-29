package tr.com.bilkent.fods.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.dto.user.DelivererDTO;
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
    public CustomHTTPResponse register(@RequestBody @Valid DelivererDTO deliverer) {
        log.debug("Deliverer register request: {}", deliverer);
        userService.register(deliverer);
        return new CustomHTTPResponse("success");
    }
}