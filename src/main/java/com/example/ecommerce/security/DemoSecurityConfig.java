package com.example.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails Asif = User.builder()
//                .username("asif")
//                .password("{noop}asif")
//                .roles("ADMIN", "EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails Roy = User.builder()
//                .username("roy")
//                .password("{noop}roy")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails Troy = User.builder()
//                .username("troy")
//                .password("{noop}troy")
//                .roles("EMPLOYEE")
//                .build();
//
//        return new InMemoryUserDetailsManager(Asif, Roy, Troy);
//    }


    @Bean
    public UserDetailsManager userDetailsManager(DataSource datasource){
        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(datasource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select id, first_name, last_name, email, pass, user_name from employees where user_name=?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select authority_id, user_name, role from authorities where user_name=?"
        );
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/about", "/contact", "/more","/Seller","/register","/existing-seller","/new-seller","/").permitAll() // Public pages
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Restricted to Admin
                        .requestMatchers("/manager/**").hasRole("MANAGER") // Restricted to Manager
                        .requestMatchers("/employee/**").hasRole("EMPLOYEE") // Restricted to Employee
                        .anyRequest().authenticated() // All other pages require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .permitAll()
                        .defaultSuccessUrl("/",true)
                        .successHandler(myAuthenticationSuccessHandler()) // Handle redirects
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
        return http.build();
    }


    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            String redirectUrl = "/login"; // Default redirection
            if (authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                redirectUrl = "/admin/home";
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_MANAGER"))) {
                redirectUrl = "/manager/home";
            } else if (authentication.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_EMPLOYEE"))) {
                redirectUrl = "/employee/home";
            }
            response.sendRedirect(redirectUrl);
        };
    }
}
