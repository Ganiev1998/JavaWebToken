package com.example.JavaWebToken.config;

import com.example.JavaWebToken.entity.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: TODO
 * @date: 26 March 2024 $
 * @time: 8:03 PM 25 $
 * @author: Qudratjon Komilov
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PrincipleUser implements UserDetails {

    private Long id;
    private String username;
    private String password;

    private List<Roles> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       // return authorities;
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
