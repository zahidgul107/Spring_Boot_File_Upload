package com.file.upload.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
 
// Entity annotation defines that a
// class can be mapped to a table
@Entity  
 
// @Table annotation defines name of the table
@Table(name = "filemodal")
public class FileModel {
	
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    @Column(name = "name")
    String fileName;
    @Lob
    @Column(name = "content")
    String content;
    @Column(name = "filetype")
    private String fileType;
 
    
    public FileModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    
    public FileModel(String fileName, String content, String fileType) {
		super();
		this.fileName = fileName;
		this.content = content;
		this.fileType = fileType;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
