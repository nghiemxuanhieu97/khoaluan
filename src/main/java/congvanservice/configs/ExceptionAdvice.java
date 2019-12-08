package congvanservice.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import springfox.documentation.spring.web.json.Json;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
