package edu.utn.Controller;


import edu.utn.Exceptions.NoContentException;
import edu.utn.Exceptions.NoTokenException;
import edu.utn.Exceptions.UserNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UserNotExistsException.class)
    public ResponseEntity handleUserNotExistException (){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

    @ExceptionHandler(NoTokenException.class)
    public ResponseEntity handleNoTokenException(){
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @ExceptionHandler(NoContentException.class)
    public ResponseEntity handleNoContentException(){
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
