package com.example.JavaWebToken.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final Filter1 filter1;
    private final CustomUserDetailService customUserDetailService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.csrf().disable()
                .cors().disable()
                .addFilterAfter(filter1,UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().disable()
                .securityMatcher("/**")
                .authorizeHttpRequests(register ->
                        register.requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/user/admin").hasRole("ROLE_USER")
                                .requestMatchers("/user/user").hasRole("ROLE_USER")
                                .anyRequest().authenticated()
                );
        return security.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity security)throws Exception{
        AuthenticationManagerBuilder managerBuilder = security.getSharedObject(AuthenticationManagerBuilder.class);
        managerBuilder
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());
        return managerBuilder.build();
    }
//    @Autowired
//    private Filter1 filter1;
//    @Autowired
//    private Filter2 filter2;
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//        security.csrf(AbstractHttpConfigurer::disable)
//                .securityMatcher("/user/**")
//                .authorizeHttpRequests(auth->
//                        auth.requestMatchers("/user/**").permitAll()
//                                .anyRequest().authenticated())
//                .addFilterBefore(filter1, UsernamePasswordAuthenticationFilter.class);
//        return security.build();
//    }
//    @Bean
//    public SecurityFilterChain filterChain2(HttpSecurity security) throws Exception {
//        security.csrf(AbstractHttpConfigurer::disable)
//                .securityMatcher("/admin/**")
//                .authorizeHttpRequests(auth->
//                        auth.requestMatchers("/admin/**").permitAll()
//                                .anyRequest().authenticated())
//                .addFilterBefore(filter2, UsernamePasswordAuthenticationFilter.class);
//        return security.build();
}
