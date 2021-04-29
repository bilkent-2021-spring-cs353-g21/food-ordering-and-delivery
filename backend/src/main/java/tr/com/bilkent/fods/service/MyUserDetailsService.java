package tr.com.bilkent.fods.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.bilkent.fods.entity.User;
import tr.com.bilkent.fods.repository.CustomerRepository;
import tr.com.bilkent.fods.repository.DelivererRepository;
import tr.com.bilkent.fods.repository.RestaurantManagerRepository;

import java.util.Collections;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class MyUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;
    private final RestaurantManagerRepository restaurantManagerRepository;
    private final DelivererRepository delivererRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = customerRepository.findById(username).orElse(null);
        String role = "ROLE_CUSTOMER";

        if (user == null) {
            user = restaurantManagerRepository.findById(username).orElse(null);
            role = "ROLE_RESTAURANT_MANAGER";
        }
        if (user == null) {
            user = delivererRepository.findById(username).orElse(null);
            role = "ROLE_DELIVERER";
        }
        if (user == null) {
            log.info("Username {} not found.", username);
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        log.info("{} log-in attempt. Username: {}", role, username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
                true, true, true, Collections.singleton(new SimpleGrantedAuthority(role)));
    }
}