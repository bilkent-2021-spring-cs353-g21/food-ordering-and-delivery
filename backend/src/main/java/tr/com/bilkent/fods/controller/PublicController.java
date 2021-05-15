package tr.com.bilkent.fods.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tr.com.bilkent.fods.controller.enums.UserType;
import tr.com.bilkent.fods.dto.district.CityDTO;
import tr.com.bilkent.fods.dto.district.DistrictDTO;
import tr.com.bilkent.fods.dto.meal.MealDTO;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.dto.search.SearchDTO;
import tr.com.bilkent.fods.dto.search.SearchResultDTO;
import tr.com.bilkent.fods.dto.user.UserDTO;
import tr.com.bilkent.fods.service.DistrictService;
import tr.com.bilkent.fods.service.RestaurantService;
import tr.com.bilkent.fods.service.SearchService;
import tr.com.bilkent.fods.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping
public class PublicController {
    private final DistrictService districtService;
    private final UserService userService;
    private final SearchService searchService;
    private final RestaurantService restaurantService;

    @Autowired
    public PublicController(DistrictService districtService,
                            UserService userService,
                            SearchService searchService,
                            RestaurantService restaurantService) {
        this.districtService = districtService;
        this.userService = userService;
        this.searchService = searchService;
        this.restaurantService = restaurantService;
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

    @ApiOperation("Search restaurants and meals.")
    @PostMapping("/search")
    public CustomHTTPResponse<SearchResultDTO> search(@RequestBody @Valid SearchDTO search,
                                                      @RequestParam(value = "page", defaultValue = "0")
                                                      @Min(0) int page,
                                                      @RequestParam(value = "limit", defaultValue = "25")
                                                      @Min(1) int limit) {
        log.info("Search request: page = {} limit = {} search = {}", page, limit, search);
        return new CustomHTTPResponse<>(searchService.search(search, page, limit));
    }

    @ApiOperation("Get meals of the restaurant sorted by type.")
    @GetMapping("/restaurant/{rid}/meals")
    public CustomHTTPResponse<List<MealDTO>> getMeals(@PathVariable("rid") long rid,
                                                      @ApiIgnore Authentication authentication) {
        log.info("Get meals request: rid = {}", rid);

        String username = authentication.getName();
        return new CustomHTTPResponse<>(restaurantService.getMeals(rid, username));
    }
}
