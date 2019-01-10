package com.first.demoMongo.restControllers;

import com.first.demoMongo.businessServices.Encrypting;
import com.first.demoMongo.dtos.PosterDto;
import com.first.demoMongo.exceptions.NotSupportedExtensionException;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.first.demoMongo.dataServices.S3Services;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping(fileResorce.FILE)
public class fileResorce {

    static final String FILE = "/file";

    @Autowired
    S3Services s3Services;

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadFile(@RequestParam() String id) throws IOException{
        return s3Services.downloadFile(id).toByteArray();
    }

    @PostMapping()
    public PosterDto uploadMultipartFile(@RequestParam MultipartFile file) throws IOException,NotSupportedExtensionException{
        if(!checkExtension(file)){
            throw new NotSupportedExtensionException(
                    "Extension "+Files.getFileExtension(file.getOriginalFilename())+
                    " is not supported allowed extensions are: jpg,jpeg,png");
        }
       PosterDto posterDto = new PosterDto(new Encrypting().encryptInBase64UrlSafe()+".jpg");
       s3Services.uploadFile(posterDto.getId(), file);
       return posterDto;
    }

    private Boolean checkExtension(MultipartFile file){
        final String[] allowedExtensions = {"jpg","jpeg","png"};
        String extension = Files.getFileExtension(file.getOriginalFilename());
        return Arrays.asList(allowedExtensions).contains(extension);
    }

    @PutMapping()
    public void updateFile(@RequestParam String id,@RequestParam MultipartFile file) throws IOException{
        s3Services.uploadFile(id,file);
    }

    @DeleteMapping()
    public void deleteFile(@RequestParam String id){
        s3Services.deleteFile(id);
    }


}
