package com.example.JavaWebToken.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @description: TODO
 * @date: 27 March 2024 $
 * @time: 7:52 PM 29 $
 * @author: Qudratjon Komilov
 */
public class UserTokenAuth extends AbstractAuthenticationToken {

    private final PrincipleUser principleUser;

    public UserTokenAuth(PrincipleUser principleUser) {
        super(principleUser.getAuthorities());
        this.principleUser = principleUser;
        setAuthenticated(principleUser.isAccountNonExpired()&&principleUser.isAccountNonLocked()&&principleUser.isCredentialsNonExpired()&&principleUser.isEnabled());
//        setAuthenticated(true);
    }
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principleUser;
    }
}
