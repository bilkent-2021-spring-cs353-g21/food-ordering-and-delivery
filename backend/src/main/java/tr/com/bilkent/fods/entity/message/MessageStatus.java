package tr.com.bilkent.fods.entity.message;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageStatus {
    SENT("SENT"),
    ARRIVED("ARRIVED"),
    READ("READ");

    private final String name;

    @Override
    public String toString() {
        return name;
    }

    @JsonValue
    public String value() {
        return name;
    }

    public static MessageStatus get(String name) {
        for (MessageStatus status : MessageStatus.class.getEnumConstants()) {
            if (status.getName().equals(name)) {
                return status;
            }
        }

        return SENT;
    }
}
