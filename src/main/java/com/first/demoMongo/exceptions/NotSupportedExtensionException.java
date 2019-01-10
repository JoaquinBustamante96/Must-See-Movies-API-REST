package com.first.demoMongo.exceptions;

public class NotSupportedExtensionException extends Exception{

    public static final String DESCRIPTION = "Not Supported extension file Exception";
    private static final long serialVersionUID = 6810752676387746340L;
    public NotSupportedExtensionException(){
        super(DESCRIPTION);
    }
    public NotSupportedExtensionException(String detail){
        super(DESCRIPTION+". "+detail);
    }

}
