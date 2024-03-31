package com.example.JavaWebToken.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.JavaWebToken.entity.RoleName;
import com.example.JavaWebToken.entity.Roles;
import com.example.JavaWebToken.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class Filter1 extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("i am working");
        request.getHeaderNames().asIterator().forEachRemaining(hederName -> {
            log.info("{},{}", hederName, request.getHeader(hederName));
        });
//      requestdan token olinib decode qilinib principleuserga map qilinib contextga beriladi
        Optional<String> token = getTokenFromRequest(request);
        if (token.isPresent()) {

            DecodedJWT decode = jwtService.decode(String.valueOf(token));
            PrincipleUser user = userService.getById(Long.valueOf(jwtService.decode(token.get().substring(7)).getSubject()));
//            PrincipleUser user = PrincipleUser.builder()
//                    .id(decode.getClaim("id").asLong())
//                    .username(decode.getClaim("username").asString())
//                    .roles(getRolesFromToken(decode))
//                    .build();

            SecurityContextHolder.getContext().setAuthentication(new UserTokenAuth(user));
        }
        filterChain.doFilter(request, response);
    }

    public Optional<String> getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return Optional.of(bearer.substring(7));
        }
        return Optional.empty();
    }

    private List<Roles> getRolesFromToken(DecodedJWT decodedJWT) {
        List<String> roles = decodedJWT.getClaim("role").asList(String.class);
        roles.stream().forEach(System.out::println);
        return roles.stream().map(RoleName::valueOf)
                .map(Roles::new).toList();
    }
}
