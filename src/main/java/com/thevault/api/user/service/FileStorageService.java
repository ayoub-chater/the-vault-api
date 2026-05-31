package com.thevault.api.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String store(MultipartFile file, String directory);

    void delete(String fileUrl);
}
