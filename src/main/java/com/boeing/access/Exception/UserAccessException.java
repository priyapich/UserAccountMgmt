package com.boeing.access.Exception;

/**
 * General runtime Exception used to throw back checked exceptions. It adds a bit of intelligence in that it adds an
 * errorCode and state which it stored based on the wrapped exception (see SQL exception)
 *
 */
public class UserAccessException extends Exception{

    private static final long serialVersionUID = 7718828512143293558L;

    private  String code;

 

    public UserAccessException(String message) {

        super(message);



    }

    public UserAccessException(String message, Throwable cause, String code) {

        super(message, cause);

        this.code = code;

    }

    public UserAccessException(String message, String code) {

        super(message);
       
        
        this.code = code;

    }

    public UserAccessException(Throwable cause, String code) {

        super(cause);

        this.code = code;

    }

    public String getCode() {

        return this.code;

    }

}