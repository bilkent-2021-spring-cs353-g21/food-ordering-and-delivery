package tr.com.bilkent.fods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.bilkent.fods.dto.user.EditUserDTO;
import tr.com.bilkent.fods.dto.user.UserDTO;
import tr.com.bilkent.fods.dto.user.UserWithoutPasswordDTO;
import tr.com.bilkent.fods.entity.User;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.deliverer.Deliverer;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;
import tr.com.bilkent.fods.exception.UsernameExistsException;
import tr.com.bilkent.fods.mapper.UserMapper;
import tr.com.bilkent.fods.repository.CustomerRepository;
import tr.com.bilkent.fods.repository.DelivererRepository;
import tr.com.bilkent.fods.repository.RestaurantManagerRepository;

@Service
public class UserService {
    private final CustomerRepository customerRepository;
    private final RestaurantManagerRepository restaurantManagerRepository;
    private final DelivererRepository delivererRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(CustomerRepository customerRepository,
                       RestaurantManagerRepository restaurantManagerRepository,
                       DelivererRepository delivererRepository,
                       PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.restaurantManagerRepository = restaurantManagerRepository;
        this.delivererRepository = delivererRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserWithoutPasswordDTO getUserWithoutPassword(String username) {
        User user = getUser(username);
        return UserMapper.INSTANCE.userToUserWithoutPasswordDto(user);
    }

    /**
     * Returns the user with the given username, or null if it does not exist.
     */
    protected User getUser(String username) {
        User user = customerRepository.findById(username).orElse(null);

        if (user == null) {
            user = restaurantManagerRepository.findById(username).orElse(null);
        }

        if (user == null) {
            user = delivererRepository.findById(username).orElse(null);
        }
        return user;
    }

    public void register(UserDTO dto, Class<? extends User> entityClass) {
        if (getUser(dto.getUsername()) != null) {
            throw new UsernameExistsException("Username " + dto.getUsername() + " is already registered");
        }

        User user = UserMapper.INSTANCE.userDtoToUser(dto, entityClass);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        getRepositoryOfEntityClassAndSave(entityClass, user);
    }

    public void edit(String username, Class<? extends User> entityClass, EditUserDTO newData) {
        String password = newData.getPassword();
        if (password != null) {
            newData.setPassword(passwordEncoder.encode(password));
        }

        User user = getUser(username);
        UserMapper.INSTANCE.updateUserFromDto(newData, user);
        getRepositoryOfEntityClassAndSave(entityClass, user);
    }

    public void delete(String username, Class<? extends User> entityClass) {
        getRepositoryOfEntityClass(entityClass).deleteById(username);
    }

    private JpaRepository<? extends User, String> getRepositoryOfEntityClass(Class<? extends User> entityClass) {
        return getRepositoryOfEntityClassAndSave(entityClass, null);
    }

    /**
     * Save the user in the appropriate repository if it is not null and return the repository.
     */
    private JpaRepository<? extends User, String> getRepositoryOfEntityClassAndSave(Class<? extends User> entityClass,
                                                                                    User user) {
        if (entityClass == Customer.class) {
            if (user != null) {
                customerRepository.save((Customer) user);
            }
            return customerRepository;

        } else if (entityClass == RestaurantManager.class) {
            if (user != null) {
                restaurantManagerRepository.save((RestaurantManager) user);
            }
            return restaurantManagerRepository;

        } else {
            if (user != null) {
                delivererRepository.save((Deliverer) user);
            }
            return delivererRepository;
        }
    }
}
