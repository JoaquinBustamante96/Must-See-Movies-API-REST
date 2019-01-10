package com.first.demoMongo.exceptions;

public class NotFoundException extends Exception {
    public static final String DESCRIPTION = "Movies Not Found Exception";
    private static final long serialVersionUID = 6830756676887746370L;

    public NotFoundException() {
        super(DESCRIPTION);
    }

    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
