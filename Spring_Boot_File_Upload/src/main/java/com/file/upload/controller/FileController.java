package com.file.upload.controller;


import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.file.upload.entity.FileModel;
import com.file.upload.service.FileServiceImplementation;

@Controller
public class FileController {
	
	@Autowired
    FileServiceImplementation fileServiceImplementation;
 
    // @GetMapping annotation for
    // mapping HTTP GET requests onto
    // specific handler methods. */
    @GetMapping("/")
    public String getData(Model model) {
    	model.addAttribute("allFiles", fileServiceImplementation.getAllFiles());
        return "multiple_files_upload";
    }
    // @PostMapping annotation maps HTTP POST
    // requests onto specific handler methods
    @PostMapping("/fileUpload")
    public String uploadMultipartFile(@RequestParam("files") MultipartFile[] files, Model modal) {
    try {
        List<FileModel> fileList = new ArrayList<FileModel>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            FileModel fileModal = new FileModel();
            fileModal.setFileName(fileName);
            fileList.add(fileModal);
            
       for (FileModel f : fileList) {
		System.err.println(f.getFileName());
	}
            // Saving all the list item into database
            fileServiceImplementation.saveAllFilesList(fileList);
        } 
            if (files != null) {
            	for (MultipartFile f : files) {
					
				
    			try {
    				File saveFile = new ClassPathResource("static/img").getFile();
    				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + f.getOriginalFilename());
        				System.out.println(path);
        				Files.copy(f.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

}
