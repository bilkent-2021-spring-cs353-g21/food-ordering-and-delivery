package tr.com.bilkent.fods.controller.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tr.com.bilkent.fods.entity.User;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.deliverer.Deliverer;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;

@AllArgsConstructor
@Getter
public enum UserType {
    CUSTOMER("customer", "ROLE_CUSTOMER", Customer.class),
    DELIVERER("deliverer", "ROLE_DELIVERER", Deliverer.class),
    MANAGER("manager", "ROLE_RESTAURANT_MANAGER", RestaurantManager.class);

    private final String name;
    private final String role;
    private final Class<? extends User> entityClass;

    @Override
    public String toString() {
        return name;
    }

    @JsonValue
    public String value() {
        return name;
    }

    public static UserType get(String name) {
        for (UserType userType : UserType.class.getEnumConstants()) {
            if (userType.getName().equals(name)) {
                return userType;
            }
        }

        return null;
    }

    public static UserType get(Class<? extends User> entityClass) {
        for (UserType userType : UserType.class.getEnumConstants()) {
            if (userType.getEntityClass().equals(entityClass)) {
                return userType;
            }
        }

        return null;
    }
}
