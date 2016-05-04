package main.java.com.bigode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RequestProblemException extends Exception {

    public RequestProblemException(){
        super();
    }

    public RequestProblemException(String message){
        super(message);
    }

    public RequestProblemException(Throwable cause){
        super(cause);
    }

    public RequestProblemException(String message, Throwable cause){
        super(message, cause);
    }
}
