package co.global.fsfb.fsfbapi.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author salajorg
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException{
	
	
}
