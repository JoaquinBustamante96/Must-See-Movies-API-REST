package com.moviesmustsee.dataServices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.amazonaws.AmazonServiceException;
import org.springframework.web.multipart.MultipartFile;

public interface S3Services {
    public ByteArrayOutputStream  downloadFile(String keyName) throws IOException, AmazonServiceException;
    public void uploadFile(String keyName, MultipartFile file) throws  AmazonServiceException,IOException;
    public void deleteFile(String id) throws AmazonServiceException;
}