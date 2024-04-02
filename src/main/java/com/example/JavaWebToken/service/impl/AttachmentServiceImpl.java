package com.example.JavaWebToken.service.impl;

import com.example.JavaWebToken.config.PrincipleUser;
import com.example.JavaWebToken.dto.FileDTO;
import com.example.JavaWebToken.entity.Attachment;
import com.example.JavaWebToken.entity.Users;
import com.example.JavaWebToken.repository.AttachmentRepository;
import com.example.JavaWebToken.repository.UserRepository;
import com.example.JavaWebToken.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Service
@RequiredArgsConstructor
@Slf4j
@EnableCaching
public class AttachmentServiceImpl implements AttachmentService {

    private final UserRepository usersRepository;
    private final AttachmentRepository attachmentRepository;

    // list of {id, name}
    @Override
    public Attachment upload(MultipartHttpServletRequest request, PrincipleUser principleUser) {
        Iterator<String> fileNames = request.getFileNames();
        log.info("File name: {}", fileNames);
        Calendar calendar = Calendar.getInstance();
        File folder = new File("/uploads/"+calendar.get(Calendar.YEAR)+"/"
                +calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.DAY_OF_MONTH));
        if (!folder.exists()) {
            folder.mkdir();
        }
        List<FileDTO> fileDTOS = new ArrayList<>();
        Optional<Users> user = usersRepository.findByUsername(principleUser.getUsername());
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            String servletFilename = UUID.randomUUID().toString();
            MultipartFile file = request.getFile(fileName);
            String extension = getExtension(file.getOriginalFilename());
            File uploadFile = new File(folder.getAbsolutePath() + "/" + servletFilename + extension);
            try {
                file.transferTo(uploadFile);
                    Attachment attachment = new Attachment();
                    attachment.setName(file.getName());
                    attachment.setPath(uploadFile.getAbsolutePath());
                    attachment.setSize(file.getSize());
                    attachment.setExtension(extension);
                    attachment.setContentType(file.getContentType());
                    attachment.setUsers(user.orElse(null));
                attachmentRepository.save(attachment);
                fileDTOS.add(FileDTO.builder()
                        .id(attachment.getId())
                        .path(attachment.getPath())
                        .size(attachment.getSize())
                        .extension(attachment.getExtension())
                        .name(attachment.getName())
                        .path(String.valueOf(ServletUriComponentsBuilder.fromCurrentContextPath().path("attachment/download/"+attachment.getId().toString())))
                        .build());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            log.info("File name: {}", fileName);
        }
        return null;
    }
    private String getExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
