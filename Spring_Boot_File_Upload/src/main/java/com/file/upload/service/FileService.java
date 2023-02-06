package com.file.upload.service;

import java.util.List;

import com.file.upload.entity.FileModel;

public interface FileService {
	
	List<FileModel> getAllFiles();
	void saveAllFilesList(List<FileModel> fileList);
}
