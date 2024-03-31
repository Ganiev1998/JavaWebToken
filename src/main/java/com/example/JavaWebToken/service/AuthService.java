package com.example.JavaWebToken.service;

import com.example.JavaWebToken.config.JwtService;
import com.example.JavaWebToken.config.PrincipleUser;
import com.example.JavaWebToken.dto.LoginRequestDTO;
import com.example.JavaWebToken.dto.LoginResponseDTO;
import com.example.JavaWebToken.dto.RegisterRequestDTO;
import com.example.JavaWebToken.dto.RegisterResponseDTO;
import com.example.JavaWebToken.entity.RoleName;
import com.example.JavaWebToken.entity.Roles;
import com.example.JavaWebToken.entity.Users;
import com.example.JavaWebToken.repository.RoleRepository;
import com.example.JavaWebToken.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CacheManager cacheManager;
    public LoginResponseDTO login(LoginRequestDTO dto){
//      Userni authenticationi olinadi
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
    public RegisterResponseDTO register(RegisterRequestDTO dto){
//        Dto orqali databazaga user passwordi encode qilib rolini user qilib create qilinadi
        Users user = Users.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
//                .role(List.of(new Roles(RoleName.USER_USER.name())))
                .role(List.of(new Roles(RoleName.ROLE_USER)))
                .build();
        userRepository.save(user);
//      frontga token qaytadi
        String uuid = String.valueOf(UUID.randomUUID());
        cacheManager.getCache("users").put(uuid,user);
        return RegisterResponseDTO.builder()
                .token(jwtService.generate(user.getId(), user.getUsername(),user.getRole()))
                .username(user.getUsername())
                .uuid(uuid)
                .build();
    }
    public String verify(String uuid){
        Users users = (Users) cacheManager.getCache("users").get(uuid);
        if (users==null){
            return "not found";
        }
        else return "successfully";
    }
}
