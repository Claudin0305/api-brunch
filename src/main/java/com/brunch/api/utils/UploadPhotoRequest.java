package com.brunch.api.utils;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadPhotoRequest {

    private MultipartFile file;
    private Long event_id;
}
