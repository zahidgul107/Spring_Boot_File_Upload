package com.file.upload.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.file.upload.entity.Images;
import com.file.upload.repository.ImagesRepository;

@Controller
public class FileUploadController {

	@Autowired
	private ImagesRepository imgRepo;
	

	@GetMapping("/fileUpload")
	public String fileUploadPage(Model model) {

		List<Images> list = imgRepo.findAll();
		model.addAttribute("list", list);
		return "file_upload";
	}

/*	@GetMapping("/mfileUpload")
	public String multipleFileUploadPage() {

		return "multiple_files_upload";
	}  */

	@PostMapping("imageUpload")
	public String imageUpload(@RequestParam MultipartFile img, HttpSession session) {

		Images im = new Images();
		im.setImageName(img.getOriginalFilename());
		Images uploadImg = imgRepo.save(im);
		if (uploadImg != null) {

			try {
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + img.getOriginalFilename());
				System.out.println(path);
				Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		session.setAttribute("msg", "Image uploaded sucessfully");

		return "redirect:/fileUpload";
	}

/*	@PostMapping("/multipleFileUpload")
	public String multipleFileUpload(@RequestParam("files") MultipartFile[] files, HttpSession session) {
		
		List<String> photos = new ArrayList<String>();
		for (MultipartFile file : files) {
			System.err.println(file.getOriginalFilename());
			photos.add(file.getOriginalFilename());
		}
		for (String ph : photos) {
			System.err.println(ph.length());
		}
		mRepo.save(photos);
		
		/*	Images im = new Images();
		im.setImageName(img.getOriginalFilename());
		Images uploadImg = imgRepo.save(im);
		if (uploadImg != null) {

			try {
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + img.getOriginalFilename());
				System.out.println(path);
				Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		session.setAttribute("msg", "Image uploaded sucessfully");  

		return "redirect:/fileUpload";	
		
	}  */

}
