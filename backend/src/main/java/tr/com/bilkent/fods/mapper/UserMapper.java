package tr.com.bilkent.fods.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import tr.com.bilkent.fods.config.MapperConfig;
import tr.com.bilkent.fods.dto.user.EditUserDTO;
import tr.com.bilkent.fods.dto.user.UserDTO;
import tr.com.bilkent.fods.dto.user.UserWithoutPasswordDTO;
import tr.com.bilkent.fods.entity.User;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.deliverer.Deliverer;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "score", ignore = true)
    @Mapping(target = "credit", ignore = true)
    Customer userDtoToCustomer(UserDTO customer);

    @Mapping(target = "worksWith", ignore = true)
    @Mapping(target = "status", ignore = true)
    Deliverer userDtoToDeliverer(UserDTO deliverer);

    RestaurantManager userDtoToManager(UserDTO manager);

    @Mapping(target = "username", ignore = true)
    @Mapping(target = "email", ignore = true)
    void updateUserFromDto(EditUserDTO userDto, @MappingTarget User user);

    @Mapping(target = "phoneNumber", ignore = true)
    UserWithoutPasswordDTO userToUserWithoutPasswordDto(User user);

    default User userDtoToUser(UserDTO dto, Class<? extends User> entityClass) {
        if (entityClass == Customer.class) {
            return userDtoToCustomer(dto);
        } else if (entityClass == Deliverer.class) {
            return userDtoToDeliverer(dto);
        } else {
            return userDtoToManager(dto);
        }
    }
}
