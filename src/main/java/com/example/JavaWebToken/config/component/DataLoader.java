package com.example.JavaWebToken.config.component;

import com.example.JavaWebToken.entity.RoleName;
import com.example.JavaWebToken.entity.Roles;
import com.example.JavaWebToken.entity.Users;
import com.example.JavaWebToken.repository.RoleRepository;
import com.example.JavaWebToken.repository.UserRepository;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository rolerepository;
    public final Lorem lorem = LoremIpsum.getInstance();
    private final PasswordEncoder encoder;
    @Override
    public void run(String... args) throws Exception {
        log.info("DataLoader is working");

        if (rolerepository.count()==0){
            for (RoleName roleName:RoleName.values()){
                rolerepository.save(new Roles(roleName));
            }
            log.info("role is appending");
        }
        if (userRepository.count()==0){
            log.info("DataLoader is working 2");
            for (int i = 0; i < 10; i++) {
                Users user = Users.builder()
                        .username(lorem.getName())
                        .role(List.of(new Roles(RoleName.ROLE_USER)))
                        .password(encoder.encode("password"))
                        .build();
                userRepository.save(user);
            }
        }
    }
}
