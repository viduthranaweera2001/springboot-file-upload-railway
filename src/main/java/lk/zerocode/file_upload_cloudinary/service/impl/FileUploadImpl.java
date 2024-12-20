package lk.zerocode.file_upload_cloudinary.service.impl;

import com.cloudinary.Cloudinary;
import lk.zerocode.file_upload_cloudinary.service.FileUpload;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FileUploadImpl implements FileUpload {

    private final Cloudinary cloudinary;
    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id",UUID.randomUUID().toString()))
                .get("url")
                .toString();
    }

    @Override
    public String findById(String publicId) {
        return cloudinary.url()
                .publicId(publicId)
                .secure(true)
                .generate();
    }
}
