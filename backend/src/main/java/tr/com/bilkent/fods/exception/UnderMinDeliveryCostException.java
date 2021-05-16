package tr.com.bilkent.fods.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UnderMinDeliveryCostException extends RuntimeException {
    private final Double current;
    private final Double minDeliveryCost;
}