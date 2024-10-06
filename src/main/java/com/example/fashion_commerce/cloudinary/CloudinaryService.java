package com.example.fashion_commerce.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {

    @Value("${cloudinary.url}")
    private String CLOUDINARY_URL;

    public List<String> uploadFiles(List<MultipartFile> multipartFiles) throws IOException {

        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        cloudinary.config.secure = true;

        List<String> finalImages = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            byte[] bytes = multipartFile.getBytes();

            // Upload the image without any transformation
            Map<String, Object> uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.emptyMap());
            String publicId = uploadResult.get("public_id").toString();

            // Generate the URL with the transformation
            String transformedUrl = cloudinary.url()
                    .transformation(new Transformation()
                            .effect("gen_background_replace:prompt_Light blue background with soft reflections"))
                    .imageTag(publicId);

            String url = transformedUrl.replaceAll(".*src='(.*?)'.*", "$1");
            finalImages.add(url);
        }

        return finalImages;
    }
}
