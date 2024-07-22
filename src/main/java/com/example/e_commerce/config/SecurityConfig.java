package com.example.e_commerce.config;

import com.example.e_commerce.entity.user.RoleCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("https://resilient-faloodeh-79ba80.netlify.app/"));
        corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));
        corsConfiguration.setAllowedHeaders(List.of(HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);

        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors().configurationSource(corsConfigurationSource());
        return httpSecurity.csrf( csrf -> csrf.disable())
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/roles/**").permitAll();
                    auth.requestMatchers("/login/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/products/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/products/add/**").hasAuthority(RoleCode.store.name());
                    auth.requestMatchers(HttpMethod.POST, "/user/address/**").hasAuthority(RoleCode.customer.name());
                    auth.requestMatchers(HttpMethod.GET, "/user/address/**").hasAuthority(RoleCode.customer.name());
                    auth.requestMatchers(HttpMethod.POST, "/user/card/**").hasAuthority(RoleCode.customer.name());
                    auth.requestMatchers(HttpMethod.POST, "/user/order/**").hasAuthority(RoleCode.customer.name());
                    auth.requestMatchers(HttpMethod.GET, "/user/order/**").hasAuthority(RoleCode.customer.name());
                    auth.requestMatchers(HttpMethod.GET, "/user/card/**").hasAuthority(RoleCode.customer.name());
                    auth.requestMatchers(HttpMethod.POST, "/products/adds/**").hasAuthority(RoleCode.store.name());
                    auth.requestMatchers(HttpMethod.GET, "/categories/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/categories/**").hasAuthority(RoleCode.admin.name());
                    auth.requestMatchers("/admin/**").hasAuthority(RoleCode.admin.name());
                    auth.requestMatchers("/customer/**").hasAnyAuthority(RoleCode.customer.name(), RoleCode.admin.name());
                    auth.requestMatchers("/store/**").hasAnyAuthority(RoleCode.store.name(), RoleCode.admin.name());
                    auth.anyRequest().authenticated();
                }).formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

}
