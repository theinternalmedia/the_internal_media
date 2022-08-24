package com.tim.utils;

import com.tim.data.TimConstants;
import com.tim.entity.NewsAndNotify;
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

    public static void uploadThumbnail(MultipartFile image, NewsAndNotify entity) throws IOException{
        ValidationUtils.validateImage(image);
        String fileName = createFileName(image, TimConstants.Upload.NEWS_PREFIX);
        uploadFile(TimConstants.Upload.THUMBNAIL_UPLOAD_DIR, fileName, image);
        String thumbnailPath = TimConstants.Upload.THUMBNAIL_PATH + fileName;
        entity.setThumbnail(thumbnailPath);
    }

    public static String uploadAvatar(MultipartFile avatar) throws IOException{
        ValidationUtils.validateImage(avatar);
        String fileName = createFileName(avatar, TimConstants.Upload.USER_PREFIX);
        uploadFile(TimConstants.Upload.USER_UPLOAD_DIR, fileName, avatar);
        String avatarPath = TimConstants.Upload.USER_PATH + fileName;
        return avatarPath;
    }
}
