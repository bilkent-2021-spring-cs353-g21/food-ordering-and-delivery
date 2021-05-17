package tr.com.bilkent.fods.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import tr.com.bilkent.fods.dto.order.DelivererOrderDTO;
import tr.com.bilkent.fods.dto.rest.CustomHTTPResponse;
import tr.com.bilkent.fods.service.DelivererService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/deliverer")
public class DelivererController {
    private final DelivererService delivererService;

    @Autowired
    public DelivererController(DelivererService delivererService) {
        this.delivererService = delivererService;
    }

    @GetMapping("/orders")
    public CustomHTTPResponse<List<DelivererOrderDTO>> getOrders(@ApiIgnore Authentication authentication) {
        log.info("Get deliverer orders request");

        String username = authentication.getName();
        return new CustomHTTPResponse<>(delivererService.getDeliveries(username));
    }
}
