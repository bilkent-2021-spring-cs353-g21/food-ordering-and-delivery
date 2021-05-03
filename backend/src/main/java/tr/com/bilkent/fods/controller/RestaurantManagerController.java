package tr.com.bilkent.fods.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.dto.user.EditUserDTO;
import tr.com.bilkent.fods.dto.user.UserDTO;
import tr.com.bilkent.fods.dto.user.UserWithoutPasswordDTO;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;
import tr.com.bilkent.fods.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/manager")
public class RestaurantManagerController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RestaurantManagerController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public CustomHTTPResponse<Void> register(@RequestBody @Valid UserDTO manager) {
        log.info("Restaurant manager register request: {}", manager);
        userService.register(manager, RestaurantManager.class);
        return new CustomHTTPResponse<>("Registration successful.");
    }

    @ApiOperation("Get the account details of a manager.")
    @GetMapping("/account")
    public CustomHTTPResponse<UserWithoutPasswordDTO> getAccount(@ApiIgnore Authentication authentication) {
        log.info("Restaurant manager get account details request");

        String username = authentication.getName();
        UserWithoutPasswordDTO user = modelMapper.map(userService.getUser(username), UserWithoutPasswordDTO.class);

        return new CustomHTTPResponse<>(user);
    }

    @ApiOperation("Edit the account of a manager.")
    @PatchMapping("/account")
    public CustomHTTPResponse<Void> editAccount(@RequestBody @Valid EditUserDTO user,
                                                @ApiIgnore Authentication authentication) {
        log.info("Restaurant manager edit account request");

        String username = authentication.getName();
        userService.edit(username, RestaurantManager.class, user);

        return new CustomHTTPResponse<>("Account successfully modified.");
    }

    @ApiOperation("Delete the account of a manager.")
    @DeleteMapping("/account")
    public CustomHTTPResponse<Void> deleteAccount(@ApiIgnore Authentication authentication) {
        log.info("Restaurant manager delete account request");

        String username = authentication.getName();
        userService.delete(username, RestaurantManager.class);

        SecurityContextHolder.clearContext();
        return new CustomHTTPResponse<>("Account deleted.");
    }
}
