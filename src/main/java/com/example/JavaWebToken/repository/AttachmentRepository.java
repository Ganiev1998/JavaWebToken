package com.example.JavaWebToken.repository;

import com.example.JavaWebToken.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
