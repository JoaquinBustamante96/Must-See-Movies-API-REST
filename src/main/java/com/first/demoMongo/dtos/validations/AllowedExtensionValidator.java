package com.first.demoMongo.dtos.validations;

import com.google.common.io.Files;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class AllowedExtensionValidator implements ConstraintValidator<AllowedExtensions, MultipartFile> {

    @Override
    public void initialize(AllowedExtensions constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {

        final String[] allowedExtensions = {"jpg","jpeg","png"};
        String extension = Files.getFileExtension(file.getOriginalFilename());

        return  Arrays.asList(allowedExtensions).contains(extension);
    }
}
