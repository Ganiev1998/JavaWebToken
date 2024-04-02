package com.example.JavaWebToken.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {
    private Long id;
    private String name;
    private String path;
    private String contentType;
    private String extension;
    private Long size;
}
