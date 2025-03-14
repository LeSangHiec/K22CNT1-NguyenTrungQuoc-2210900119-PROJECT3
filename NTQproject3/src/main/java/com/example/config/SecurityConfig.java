package com.example.config;

import com.example.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    // Cấu hình bảo mật cho admin
    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
          .securityMatcher("/admin/**")
          .authorizeHttpRequests(authz -> authz
              .anyRequest().hasRole("QUAN_TRI")
          )
          .userDetailsService(customUserDetailsService)
          .formLogin(form -> form
              .loginPage("/admin/login")
              .loginProcessingUrl("/admin/login")
              .defaultSuccessUrl("/admin", true)
              .permitAll()
          )
          .logout(logout -> logout
              .logoutUrl("/admin/logout")
              .logoutSuccessUrl("/admin/login?logout")
              .permitAll()
          )
          .csrf(csrf -> csrf.disable());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // Cho phép truy cập tất cả các tài nguyên tĩnh mà không yêu cầu xác thực
                .requestMatchers("/**").permitAll()
                // Các đường dẫn yêu cầu đăng nhập
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/")
                .permitAll()
            .and()
            .logout()
                .permitAll();
        return http.build();
    }

    // Cấu hình cho phép các tài nguyên tĩnh được phục vụ đúng cách
    
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .requestMatchers("/frontend/**", "/css/**", "/js/**", "/images/**", "/vendors/**");
    }


    

}
