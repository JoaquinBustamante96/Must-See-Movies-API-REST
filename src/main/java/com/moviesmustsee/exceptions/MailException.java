package com.moviesmustsee.exceptions;

public class MailException extends ConflictException {
    public static final String DESCRIPTION = "Error Sending Mail:";
    private static final long serialVersionUID = 6830756676887746370L;

    public MailException() {
        super(DESCRIPTION);
    }
    public MailException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
