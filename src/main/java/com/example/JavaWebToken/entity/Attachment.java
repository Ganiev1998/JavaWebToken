package com.example.JavaWebToken.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attachment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String path;
    private String contentType;
    private String extension;
    private Long size;
    @ManyToOne
    private Users users;
}
