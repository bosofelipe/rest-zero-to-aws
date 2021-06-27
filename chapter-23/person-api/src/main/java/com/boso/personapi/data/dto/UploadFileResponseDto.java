package com.boso.personapi.data.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UploadFileResponseDto implements Serializable {

  private String fileName;
  private String fileDownloadUri;
  private String fileType;
  private long size;

}
