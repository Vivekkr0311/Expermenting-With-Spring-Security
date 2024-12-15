package Learning_JWT.configs;

import Learning_JWT.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    Not necessarily required, as it already being configured by Spring Security when Component class of UserDetailServiceImpl is created.
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*  1. No Need for DaoAuthenticationProvider
    By default, Spring Security auto-configures a DaoAuthenticationProvider if it finds:
    A UserDetailsService bean.
    A PasswordEncoder bean.
    Since you have both (UserDetailsServiceImpl and BCryptPasswordEncoder), Spring Security registers a DaoAuthenticationProvider behind the scenes.
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
*/

/*    Similar to DaoAuthenticationProvider, Spring Security automatically configures an AuthenticationManager when:
      An AuthenticationProvider is present.
      Since the default configuration works for your use case, the explicit AuthenticationManager bean is unnecessary.
      @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
      }
 */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/user/**").permitAll() // Public endpoints
                        .requestMatchers("/data/**").authenticated() // Protected endpoints
                );
        http.httpBasic(httpSecurityHttpBasicConfigurer -> {});
//        Below is already being configured by Spring Security when UserDetailServiceImpl is created.
//        http.authenticationProvider(authenticationProvider());
        return http.build();
    }
}
