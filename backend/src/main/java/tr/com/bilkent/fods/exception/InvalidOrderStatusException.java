package tr.com.bilkent.fods.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import tr.com.bilkent.fods.entity.order.OrderStatus;

@Getter
@AllArgsConstructor
@ToString
public class InvalidOrderStatusException extends RuntimeException {
    private final Long oid;
    private final OrderStatus actual;
    private final OrderStatus expected;
}