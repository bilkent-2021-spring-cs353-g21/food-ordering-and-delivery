package tr.com.bilkent.fods.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.bilkent.fods.entity.User;
import tr.com.bilkent.fods.entity.customer.Customer;
import tr.com.bilkent.fods.entity.restaurantmanager.RestaurantManager;

import java.util.Collections;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class MyUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
        if (user == null) {
            log.info("Username {} not found.", username);
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        String role;
        if (user instanceof Customer) {
            role = "ROLE_CUSTOMER";
        } else if (user instanceof RestaurantManager) {
            role = "ROLE_RESTAURANT_MANAGER";
        } else {
            role = "ROLE_DELIVERER";
        }

        log.info("{} log-in attempt. Username: {}", role, username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
                true, true, true, Collections.singleton(new SimpleGrantedAuthority(role)));
    }
}