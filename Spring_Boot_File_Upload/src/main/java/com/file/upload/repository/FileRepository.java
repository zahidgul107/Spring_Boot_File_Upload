package com.file.upload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.file.upload.entity.FileModel;

@Repository
public interface FileRepository extends JpaRepository<FileModel, Long>{
	
	

}
