package tr.com.bilkent.fods.controller.enums;

import org.springframework.core.convert.converter.Converter;

public class UserTypeConverter implements Converter<String, UserType> {
    @Override
    public UserType convert(String from) {
        return UserType.get(from);
    }
}
