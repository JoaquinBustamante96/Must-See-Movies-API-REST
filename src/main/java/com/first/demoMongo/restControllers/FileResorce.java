package com.first.demoMongo.restControllers;

import com.first.demoMongo.businessServices.Encrypting;
import com.first.demoMongo.dtos.PosterDto;
import com.first.demoMongo.dtos.validations.AllowedExtensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.first.demoMongo.dataServices.S3Services;

import java.io.IOException;

@RestController
@RequestMapping(FileResorce.FILE)
@PreAuthorize("hasRole('ADMIN')")
public class FileResorce {

    static final String FILE = "/file";

    @Autowired
    S3Services s3Services;

    @PostMapping()
    public PosterDto uploadMultipartFile(@RequestParam @AllowedExtensions MultipartFile file)
            throws IOException {
        PosterDto posterDto = new PosterDto(new Encrypting().encryptInBase64UrlSafe() + ".jpg");
        s3Services.uploadFile(posterDto.getId(), file);
        return posterDto;
    }

    @PutMapping()
    public void updateFile(@RequestParam String id,
                           @RequestParam @AllowedExtensions MultipartFile file)
            throws IOException {
        s3Services.uploadFile(id, file);
    }

    @DeleteMapping()
    public void deleteFile(@RequestParam String id) {
        s3Services.deleteFile(id);
    }


}
