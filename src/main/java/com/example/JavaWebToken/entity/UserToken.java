package com.example.JavaWebToken.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private String code;
    @Column(precision = 3)
    private int count;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    private Users user;

}
