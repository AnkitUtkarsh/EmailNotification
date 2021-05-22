package com.email;

import java.io.PrintWriter;
import java.io.StringWriter;

public class EmailNotificationException extends Exception {

    private static final long serialVersionUID=1L;
    private String message = null;

    public EmailNotificationException(String message){
        super(message);
        this.message=message;
    }
    
    public EmailNotificationException(Throwable cause){
        super(cause);
    }

    public EmailNotificationException(String message, Throwable cause){
        super(message, cause);
        this.message=message;

    }
    public String getMessage(){
        return message;
    }

    public String getExceptionTrace(){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        this.printStackTrace(pw);
        return sw.toString();
        
    }
}
