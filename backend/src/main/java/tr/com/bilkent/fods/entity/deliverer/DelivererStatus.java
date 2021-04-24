package tr.com.bilkent.fods.entity.deliverer;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DelivererStatus {
    AVAILABLE("AVAILABLE"),
    NOT_AVAILABLE("NOT_AVAILABLE");

    private final String name;

    @Override
    public String toString() {
        return name;
    }

    @JsonValue
    public String value() {
        return name;
    }

    public static DelivererStatus get(String name) {
        for (DelivererStatus status : DelivererStatus.class.getEnumConstants()) {
            if (status.getName().equals(name)) {
                return status;
            }
        }

        return AVAILABLE;
    }
}
