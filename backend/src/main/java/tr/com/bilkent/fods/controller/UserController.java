package tr.com.bilkent.fods.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tr.com.bilkent.fods.controller.enums.UserType;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.dto.user.EditUserDTO;
import tr.com.bilkent.fods.dto.user.UserWithoutPasswordDTO;
import tr.com.bilkent.fods.service.UserService;

import javax.validation.Valid;

@Slf4j
@RestController
@Validated
@RequestMapping("/{userType}")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Get the account details of the logged-in user.")
    @GetMapping("/account")
    public CustomHTTPResponse<UserWithoutPasswordDTO> getAccount(@PathVariable UserType userType,
                                                                 @ApiIgnore Authentication authentication) {
        log.info("{} get account details request", userType.getName());

        String username = authentication.getName();
        UserWithoutPasswordDTO user = userService.getUserWithoutPassword(username);

        return new CustomHTTPResponse<>(user);
    }

    @ApiOperation("Edit the account of the logged-in user.")
    @PatchMapping("/account")
    public CustomHTTPResponse<Void> editAccount(@PathVariable UserType userType,
                                                @RequestBody @Valid EditUserDTO user,
                                                @ApiIgnore Authentication authentication) {
        log.info("{} edit account request", userType.getName());

        String username = authentication.getName();
        userService.edit(username, userType.getEntityClass(), user);

        return new CustomHTTPResponse<>("Account successfully modified.");
    }
}
