package tr.com.bilkent.fods.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NonExistsOrderException extends RuntimeException {
    private final Long oid;
}