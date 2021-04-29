package tr.com.bilkent.fods.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static javax.servlet.http.HttpServletResponse.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@AllArgsConstructor(onConstructor_ = @Autowired)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and()

                .authorizeRequests()
                .antMatchers("/**/register").permitAll()
                .antMatchers("/api/customer/**").hasRole("CUSTOMER")
                .antMatchers("/api/deliverer/**").hasRole("DELIVERER")
                .antMatchers("/api/manager/**").hasRole("RESTAURANT_MANAGER").and()

                .exceptionHandling()
                .accessDeniedHandler((req, resp, ex) -> resp.setStatus(SC_FORBIDDEN))
                .authenticationEntryPoint((req, resp, ex) -> resp.setStatus(SC_UNAUTHORIZED)).and()

                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler((req, resp, auth) -> resp.setStatus(SC_OK))
                .failureHandler((req, resp, ex) -> resp.setStatus(SC_FORBIDDEN)).and()

                .sessionManagement().invalidSessionStrategy((req, resp) -> resp.setStatus(499)).and()

                .logout().logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "Cookie",
                "Referer", "User-Agent", "Set-Cookie"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "Cookie",
                "Referer", "User-Agent", "Set-Cookie"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}