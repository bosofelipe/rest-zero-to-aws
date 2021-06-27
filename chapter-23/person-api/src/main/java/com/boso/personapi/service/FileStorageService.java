package com.boso.personapi.service;

import com.boso.personapi.config.FileStorageConfig;
import com.boso.personapi.exception.FileNotFoundException;
import com.boso.personapi.exception.FileStorageException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

  private Path fileStorageLocation;

  @Autowired
  public FileStorageService(FileStorageConfig fileStorageConfig) {
    this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath()
        .normalize();
    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (Exception e) {
      throw new FileStorageException(
          "Could not create the directory where the upload files will be stored", e);
    }
  }

  public String storageFile(MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    try {
      if (fileName.contains("..")) {
        throw new FileStorageException("Sorry filename contains invalid path sequence " + fileName);
      }

      Path targetLocationPath = this.fileStorageLocation.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocationPath, StandardCopyOption.REPLACE_EXISTING);

      return fileName;
    } catch (Exception e) {
      throw new FileStorageException("Could not store file" + fileName + "Please try again!", e);
    }
  }

  public Resource loadFileResource(String fileName) {
    try {
      Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists()) {
        return resource;
      } else {
        throw new FileNotFoundException("File not found " + fileName);
      }
    } catch (Exception e) {
      throw new FileNotFoundException("File not found " + fileName, e);
    }
  }

}
