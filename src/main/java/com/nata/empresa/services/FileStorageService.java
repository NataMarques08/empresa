package com.nata.empresa.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nata.empresa.config.FileConfg.FileStorageConfig;
import com.nata.empresa.exceptions.FileStorageExceptions;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();  
        this.fileStorageLocation = path;
        
        try{
            Files.createDirectories(this.fileStorageLocation); 
        }catch(Exception e){
            throw new FileStorageExceptions("Could not create the directory where the uploaded files will be stored.", e);
        }   
    
    }

    public String storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(fileName.contains("..")){
                throw new FileStorageExceptions("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);

            return fileName;
        }catch(Exception e){
            throw new FileStorageExceptions("Could not store file " + fileName + ". Please try again!", e);
        }

    }

}
