package com.tim.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

public class ImageFileUploadUtil {
    public static void uploadFile(String uploadDir, String fileName,
                                  MultipartFile multipartFile) throws IOException {

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Files.deleteIfExists(Paths.get(uploadDir + fileName));

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static String createFileName(MultipartFile image, String prefixFileName){
        StringBuilder fileName = new StringBuilder(prefixFileName).append(new Date().getTime());
        String extension = FilenameUtils.getExtension(image.getOriginalFilename());
        fileName.append("." + extension);
        return fileName.toString();
    }
}
