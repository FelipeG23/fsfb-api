package co.global.fsfb.fsfbapi.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author salajorg
 */
@RestControllerAdvice
@Slf4j
public class FsfbExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler
    public final ResponseEntity handleConflict(Exception ex) {
       
//        log.error("Error no controlado: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        
    }
}
