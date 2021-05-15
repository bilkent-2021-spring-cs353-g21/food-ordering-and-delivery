package tr.com.bilkent.fods.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderNotFromRestaurantException extends RuntimeException {
    private final Long oid;
    private final Long rid;
}