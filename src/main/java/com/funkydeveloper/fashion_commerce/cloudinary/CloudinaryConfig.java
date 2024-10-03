package com.funkydeveloper.fashion_commerce.cloudinary;

import com.cloudinary.Cloudinary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class CloudinaryConfig {

    @Value("${cloudinary-url}")
    private String CLOUDINARY_URL;

    public void uploadImageToCloudinary(MultipartFile image) throws IOException {
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        cloudinary.config.secure = true;

        Map<String, Boolean> params  = Map.of(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        var uploadedImage = cloudinary.uploader().upload(image, params);

        log.info("the cloudinary info {}", uploadedImage);
    }
}
