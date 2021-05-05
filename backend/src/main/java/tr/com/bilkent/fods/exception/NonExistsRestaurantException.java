package tr.com.bilkent.fods.exception;

import lombok.Getter;

@Getter
public class NonExistsRestaurantException extends RuntimeException {
    private final Long rid;

    public NonExistsRestaurantException(final Long rid) {
        this.rid = rid;
    }
}