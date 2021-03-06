package nl.victorfdt.projectmanagerbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UniqueKeyViolationException extends RuntimeException {

    public UniqueKeyViolationException(String message) {
        super(message);
    }
}
