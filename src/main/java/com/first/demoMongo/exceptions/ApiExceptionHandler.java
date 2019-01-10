package com.first.demoMongo.exceptions;

import com.amazonaws.AmazonServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class,
            BadRequestException.class,
            NotSupportedExtensionException.class,
    })
    @ResponseBody
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            org.springframework.security.access.AccessDeniedException.class,

    })
    @ResponseBody
    public ErrorMessage forbiddenRequest(HttpServletRequest request,Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ExceptionHandler({
            AmazonServiceException.class,
    })
    @ResponseBody
    public ErrorMessage amazonException(HttpServletRequest request,AmazonServiceException exception){
        return new ErrorMessage(
                         "Error Message: "+ exception.getMessage()+
                         "Http status code: "+exception.getStatusCode()+
                         "Aws Error Code: "+exception.getErrorCode()+
                         "Error Type:" + exception.getErrorType()+
                         "Request ID: " + exception.getRequestId()
        );}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class,
            IOException.class,
    })
    @ResponseBody
    public ErrorMessage exception(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage("Server Error. " + exception.getMessage());
    }

}
