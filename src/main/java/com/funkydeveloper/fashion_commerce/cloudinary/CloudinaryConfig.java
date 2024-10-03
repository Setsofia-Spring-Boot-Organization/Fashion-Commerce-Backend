package com.funkydeveloper.fashion_commerce.cloudinary;

import com.cloudinary.Cloudinary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CloudinaryConfig {

    @Value("${cloudinary-url}")
    private String CLOUDINARY_URL;

    public List<String> uploadImageToCloudinary(List<MultipartFile> multipartFiles) throws IOException {
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        cloudinary.config.secure = true;

        Map<String, Boolean> params  = Map.of(
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        List<String> finalImages = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {

            byte[] bytes = multipartFile.getBytes();

            var uploadedImage = cloudinary.uploader().upload(bytes, params);
            finalImages.add(uploadedImage.get("secure_url").toString());
        }

        return finalImages;
    }
}
