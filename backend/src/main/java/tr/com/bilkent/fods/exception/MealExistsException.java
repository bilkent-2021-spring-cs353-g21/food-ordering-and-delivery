package tr.com.bilkent.fods.exception;

import lombok.Getter;

@Getter
public class MealExistsException extends RuntimeException {
    private final Long rid;
    private final String name;

    public MealExistsException(final Long rid, final String name) {
        this.rid = rid;
        this.name = name;
    }
}