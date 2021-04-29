package tr.com.bilkent.fods.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.bilkent.fods.dto.user.CustomerDTO;
import tr.com.bilkent.fods.dto.user.DelivererDTO;
import tr.com.bilkent.fods.dto.user.RestaurantManagerDTO;
import tr.com.bilkent.fods.dto.user.UserDTO;
import tr.com.bilkent.fods.entity.User;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.deliverer.Deliverer;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;
import tr.com.bilkent.fods.exception.UsernameExistsException;
import tr.com.bilkent.fods.repository.CustomerRepository;
import tr.com.bilkent.fods.repository.DelivererRepository;
import tr.com.bilkent.fods.repository.RestaurantManagerRepository;

@Service
public class UserService {
    private final CustomerRepository customerRepository;
    private final RestaurantManagerRepository restaurantManagerRepository;
    private final DelivererRepository delivererRepository;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(CustomerRepository customerRepository,
                       RestaurantManagerRepository restaurantManagerRepository,
                       DelivererRepository delivererRepository,
                       UserDetailsService userDetailsService,
                       PasswordEncoder passwordEncoder) {
        this.modelMapper = new ModelMapper();

        this.customerRepository = customerRepository;
        this.restaurantManagerRepository = restaurantManagerRepository;
        this.delivererRepository = delivererRepository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(CustomerDTO dto) {
        register(dto, Customer.class);
    }

    public void register(RestaurantManagerDTO dto) {
        register(dto, RestaurantManager.class);
    }

    public void register(DelivererDTO dto) {
        register(dto, Deliverer.class);
    }

    private void register(UserDTO dto, Class<? extends User> entityClass) {
        try {
            UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());
            throw new UsernameExistsException(
                    "Username " + dto.getUsername() + " is already registered as " + user.getAuthorities());
        } catch (UsernameNotFoundException e) {
            // Username does not exist. Continue registration process.
        }

        User user = modelMapper.map(dto, entityClass);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        if (entityClass == Customer.class) {
            customerRepository.save((Customer) user);
        } else if (entityClass == RestaurantManager.class) {
            restaurantManagerRepository.save((RestaurantManager) user);
        } else {
            delivererRepository.save((Deliverer) user);
        }
    }
}
