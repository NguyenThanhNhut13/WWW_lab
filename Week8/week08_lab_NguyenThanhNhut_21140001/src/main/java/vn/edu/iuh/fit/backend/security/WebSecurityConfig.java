/*
 * @ (#) SecurityConfig.java       1.0     07/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.security;
/*
 * @description:
 * @author: Nguyen Thanh Nhut
 * @date: 07/11/2024
 * @version:    1.0
 */

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.edu.iuh.fit.backend.services.UserService;
import vn.edu.iuh.fit.backend.services.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
            configurer -> configurer
                    .requestMatchers(HttpMethod.GET, "/home", "/", "/css/**", "/js/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/jobs/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/jobs/{id}/apply").hasAnyAuthority("USER", "CANDIDATE", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/jobs/{id}/apply").hasAnyAuthority("USER", "CANDIDATE", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/company/account-registration").hasAnyAuthority("USER", "COMPANY", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/company").hasAnyAuthority("COMPANY", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/company/**").hasAnyAuthority("COMPANY", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/candidates").hasAnyAuthority("COMPANY", "ADMIN")

                    .requestMatchers(HttpMethod.GET, "/api/users/current-user").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/jobs").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/jobs/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/job-application").hasAnyAuthority("USER", "CANDIDATE", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/job-application/**").hasAnyAuthority("COMPANY", "ADMIN")
                    .requestMatchers(HttpMethod.POST, "/api/jobs").hasAnyAuthority("COMPANY", "ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/skills").permitAll()
                    .requestMatchers(HttpMethod.GET, "/api/companies").hasAnyAuthority("ADMIN", "COMPANY")
                    .requestMatchers(HttpMethod.GET, "/api/companies/**").hasAnyAuthority("USER", "ADMIN", "COMPANY")

        ).formLogin(
                formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/do-login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
        ).
        logout(
                LogoutConfigurer::permitAll
        );

        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();

//        http
//
//                .authorizeRequests()
//                .requestMatchers("/login/**")
//                .permitAll()
//                .and()
//                .formLogin(
//                        formLogin -> formLogin
//                                .loginPage("/login")
//                                .loginProcessingUrl("/do-login")
//                                .defaultSuccessUrl("/home", true)
//                                .permitAll()
//                )
//                .logout(
//                        logout -> logout
//                                .invalidateHttpSession(true)
//                                .clearAuthentication(true)
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .logoutSuccessUrl("/login?logout")
//                                .permitAll()
//                );
//
//        // Require authentication for all requests
//        http.httpBasic(AbstractHttpConfigurer::disable);
//
//        // Disable CSRF
//        http.csrf(AbstractHttpConfigurer::disable);
//
//        return http.build();
    }


}
