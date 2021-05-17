package tr.com.bilkent.fods.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bilkent.fods.dto.order.DelivererOrderDTO;
import tr.com.bilkent.fods.entity.order.Order;
import tr.com.bilkent.fods.mapper.OrderMapper;
import tr.com.bilkent.fods.repository.OrderRepository;

import java.util.List;

@Slf4j
@Service
public class DelivererService {
    private final OrderRepository orderRepository;

    @Autowired
    public DelivererService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<DelivererOrderDTO> getDeliveries(String username) {
        List<Order> orders = orderRepository.getOrdersWaitingForDelivery(username);
        return OrderMapper.INSTANCE.ordersToDelivererOrderDtos(orders);
    }
}
