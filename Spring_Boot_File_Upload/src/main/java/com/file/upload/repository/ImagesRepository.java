package com.file.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.file.upload.entity.Images;

public interface ImagesRepository extends JpaRepository<Images, Long>{

	

}
