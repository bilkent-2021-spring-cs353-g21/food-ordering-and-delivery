package tr.com.bilkent.fods.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.com.bilkent.fods.controller.enums.UserType;
import tr.com.bilkent.fods.dto.district.CityDTO;
import tr.com.bilkent.fods.dto.district.DistrictDTO;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.dto.user.UserDTO;
import tr.com.bilkent.fods.service.DistrictService;
import tr.com.bilkent.fods.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping
public class PublicController {
    private final DistrictService districtService;
    private final UserService userService;

    @Autowired
    public PublicController(DistrictService districtService, UserService userService) {
        this.districtService = districtService;
        this.userService = userService;
    }

    @PostMapping("/{userType}/register")
    public CustomHTTPResponse<Void> register(@PathVariable("userType") UserType userType,
                                             @RequestBody @Valid UserDTO user) {
        log.info("{} register request: {}", userType.getName(), user);
        userService.register(user, userType.getEntityClass());
        return new CustomHTTPResponse<>("Registration successful.");
    }

    @ApiOperation("Get cities.")
    @GetMapping("/cities")
    public CustomHTTPResponse<Set<CityDTO>> getCities() {
        log.info("Get cities request");
        return new CustomHTTPResponse<>(districtService.getCities());
    }

    @ApiOperation("Get districts of the city.")
    @GetMapping("/districts/{cityName}")
    public CustomHTTPResponse<List<DistrictDTO>> getDistricts(@PathVariable("cityName") String cityName) {
        log.info("Get districts of city request: {}", cityName);
        return new CustomHTTPResponse<>(districtService.getDistrictsOfCity(cityName));
    }
}
