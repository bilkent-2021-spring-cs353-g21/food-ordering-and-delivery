package tr.com.bilkent.fods.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NonExistsMealException extends RuntimeException {
    private final Long rid;
    private final String mealName;
}