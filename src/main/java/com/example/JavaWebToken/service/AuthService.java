package com.example.JavaWebToken.service;

import com.example.JavaWebToken.config.JwtService;
import com.example.JavaWebToken.config.PrincipleUser;
import com.example.JavaWebToken.dto.LoginRequestDTO;
import com.example.JavaWebToken.dto.LoginResponseDTO;
import com.example.JavaWebToken.dto.RegisterRequestDTO;
import com.example.JavaWebToken.entity.RoleName;
import com.example.JavaWebToken.entity.Roles;
import com.example.JavaWebToken.entity.UserToken;
import com.example.JavaWebToken.entity.Users;
import com.example.JavaWebToken.repository.UserRepository;
import com.example.JavaWebToken.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    private final UserRepository userRepository;
    //private final CacheManager cacheManager;
    private final UserTokenRepository userTokenRepository;
    private final EmailService emailService;
    public LoginResponseDTO login(LoginRequestDTO dto){
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));
//      PrincipleUserga cast qilinadi
        PrincipleUser user = (PrincipleUser) authentication.getPrincipal();
//      Contextga beriladi
        SecurityContextHolder.getContext().setAuthentication(authentication);
//      token yaratiladi
        String token =jwtService.generate(user.getId(), user.getUsername(),user.getRoles());
//      DTO qaytariladi
        return LoginResponseDTO.builder()
                .token(token)
                .build();
    }
    public String register(RegisterRequestDTO dto){
//        Dto orqali databazaga user passwordi encode qilib rolini user qilib create qilinadi
        Users user = Users.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(List.of(new Roles(RoleName.ROLE_USER)))
                .active(false)
                .build();
        userRepository.save(user);
//      frontga token qaytadi
        String uuid = String.valueOf(UUID.randomUUID());
        String code = RandomStringUtils.random(10,false,true);
        UserToken userToken = userTokenRepository.save(UserToken.builder()
                .token(uuid)
                .code(code)
                .user(user)
                .build());
        return userToken.getToken();
    }
    public String verify(String uuid){
        Optional<UserToken> users = userTokenRepository.findByToken(uuid);
        if (users==null){
            return "not found";
        }
        else return "successfully";
    }
}
