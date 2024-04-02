package com.example.JavaWebToken.service;

import com.example.JavaWebToken.config.PrincipleUser;
import com.example.JavaWebToken.entity.Attachment;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AttachmentService {
    Attachment upload(MultipartHttpServletRequest request, PrincipleUser principleUser);

}
