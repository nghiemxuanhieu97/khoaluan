package congvanservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceExistException extends Exception {
    private static final long serialVersionUID = 1L;
    public ResourceExistException(String messeage){
        super(messeage);
    }
}
