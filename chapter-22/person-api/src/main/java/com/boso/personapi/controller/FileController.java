package com.boso.personapi.controller;

import com.boso.personapi.data.dto.UploadFileResponseDto;
import com.boso.personapi.service.FileStorageService;
import io.swagger.annotations.Api;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = "FileEndpoint")
@RestController
@RequestMapping("/api/file/v1")
@Log4j2
public class FileController {

  private final FileStorageService fileStorageService;

  @Autowired
  public FileController(final FileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
  }


  @PostMapping("/uploadFile")
  public UploadFileResponseDto uploadFile(@RequestParam("file") MultipartFile file) {
    String fileName = fileStorageService.storageFile(file);

    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(""
        + "/api/file/v1/downloadFile/")
        .path(fileName).toUriString();

    return UploadFileResponseDto.builder()
        .fileName(fileName)
        .fileDownloadUri(fileDownloadUri)
        .fileType(file.getContentType())
        .size(file.getSize())
        .build();
  }

  @PostMapping("/uploadMultipleFiles")
  public List<UploadFileResponseDto> uploadMultipleFiles(
      @RequestParam("files") MultipartFile[] files) {
    return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
  }

  @GetMapping("/downloadFile/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,
      HttpServletRequest request) {
    Resource resource = fileStorageService.loadFileResource(fileName);

    String contentType = null;
    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (Exception e) {
      log.error("Could not determine file type");
    }
    if (contentType == null) {
      contentType = "application/octet.stream";
    }

    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }
}
