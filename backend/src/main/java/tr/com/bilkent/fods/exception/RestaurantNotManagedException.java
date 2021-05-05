package tr.com.bilkent.fods.exception;

import lombok.Getter;

@Getter
public class RestaurantNotManagedException extends RuntimeException {
    private final Long rid;

    public RestaurantNotManagedException(final Long rid) {
        this.rid = rid;
    }
}