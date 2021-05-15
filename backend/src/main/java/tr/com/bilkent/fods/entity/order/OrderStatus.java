package tr.com.bilkent.fods.entity.order;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    WAITING("WAITING"),
    COOKING("COOKING"),
    WAITING_FOR_DELIVERER("WAITING_FOR_DELIVERER"),
    DELIVERING("DELIVERING"),
    COMPLETED("COMPLETED"),
    CANCELED("CANCELED");

    private final String name;

    @Override
    public String toString() {
        return name;
    }

    @JsonValue
    public String value() {
        return name;
    }

    public static OrderStatus get(String name) {
        for (OrderStatus status : OrderStatus.class.getEnumConstants()) {
            if (status.getName().equals(name)) {
                return status;
            }
        }

        return WAITING;
    }
}
