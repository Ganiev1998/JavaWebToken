package com.example.JavaWebToken.controller;

import com.example.JavaWebToken.config.PrincipleUser;
import com.example.JavaWebToken.entity.Attachment;
import com.example.JavaWebToken.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
@Slf4j
public class AttachmentController {

    private final AttachmentService attachmentService;


    @PostMapping("/upload")
    public Attachment upload(MultipartHttpServletRequest request, @AuthenticationPrincipal PrincipleUser principleUser) {
        return attachmentService.upload(request, principleUser);
    }


}
