package com.example.JavaWebToken.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Roles {
    @Id
    @Enumerated(EnumType.STRING)
    private RoleName role;
}
