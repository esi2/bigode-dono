package main.java.com.bigode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SystemErrorException extends Exception {

    public SystemErrorException(){
        super();
    }

    public SystemErrorException(String message){
        super(message);
    }

    public SystemErrorException(Throwable cause){ super(cause); }

    public SystemErrorException(String message, Throwable cause){ super(message, cause); }
}
