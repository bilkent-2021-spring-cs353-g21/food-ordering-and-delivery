package tr.com.bilkent.fods.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.dto.user.UserDTO;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final UserService userService;

    @Autowired
    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public CustomHTTPResponse<Void> register(@RequestBody @Valid UserDTO customer) {
        log.info("Customer register request: {}", customer);
        userService.register(customer, Customer.class);
        return new CustomHTTPResponse<>("success");
    }
}
