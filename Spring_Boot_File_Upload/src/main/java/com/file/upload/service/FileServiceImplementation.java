package com.file.upload.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.file.upload.entity.FileModel;
import com.file.upload.repository.FileRepository;

@Service
public class FileServiceImplementation implements FileService {

	// @Autowired annotation used to inject
    // the object dependency of FileRepository
    @Autowired 
    FileRepository fileRepository;
   
    @Override
    public List<FileModel> getAllFiles() {
        // fetch all the files form database
        return fileRepository.findAll();
    }

	@Override
	public void saveAllFilesList(List<FileModel> fileList) {
		
		for (FileModel fileModel : fileList) {
			fileRepository.save(fileModel);
		}
		
	}

}
