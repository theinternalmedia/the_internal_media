package com.tim.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.tim.data.ETimMessages;
import com.tim.data.TimConstants;
import com.tim.exception.ValidateException;

public class UploadUtil {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UploadUtil.class);
	private static final String RESOURCE = "upload";
	
	/**
	 * Save upload file
	 * 
	 * @author minhtuanitk43
	 * @param multipartFile
	 * @param uploadDir
	 * @param fileName
	 * @throws IOException
	 */
    public static void uploadFile(MultipartFile multipartFile, String uploadDir, String fileName) 
    		throws IOException {
        try (InputStream inputStream = multipartFile.getInputStream()) {
        	Path uploadPath = Paths.get(RESOURCE + uploadDir);
            if (!Files.exists(uploadPath)) {
            	Files.createDirectories(uploadPath);
            }
            // Delete if exists
            delelteFile(uploadDir + fileName);
            
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
        	logger.error("An error occured while saving th file:{}", fileName);
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    /**
     * Delete file
     * 
     * @author minhtuanitk43
     * @param filePath
     * @throws IOException
     */
	public static void delelteFile(String filePath) throws IOException {
		try {
			Files.deleteIfExists(Paths.get(RESOURCE + filePath));
		} catch (IOException e) {
			logger.error("An error occured while deleting th file:{}", filePath, 
					e.getStackTrace().toString());
			throw new IOException();
		}
	}
	
	/**
	 * Upload image file
	 * 
	 * @author minhtuanitk43
	 * @param file
	 * @param path
	 * @param fileName
	 * @throws IOException
	 */
    public static String uploadImage(MultipartFile file, String path, String fileName) 
    		throws IOException {
    	validateImage(file);
    	String extension = FilenameUtils.getExtension(file.getOriginalFilename());
    	fileName = Utility.formatFileName(fileName);
        fileName += "." + extension;
        uploadFile(file, path, fileName);
        return path + fileName;
        
    }

    /**
     * Validate image format
     * 
     * @author minhtuanitk43
     * @param image
     */
    private static void validateImage(MultipartFile image){
		String imageExtension = FilenameUtils.getExtension(image.getOriginalFilename());
		if(!TimConstants.Upload.IMAGE_MIME_TYPE.contains(imageExtension)){
			throw new ValidateException(ETimMessages.INVALID_IMAGE_FILE, null, 
					image.getOriginalFilename());
		}
	}
}
