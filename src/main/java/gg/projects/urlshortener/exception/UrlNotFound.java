package gg.projects.urlshortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gohar.gasparyan
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UrlNotFound extends RuntimeException {

    public UrlNotFound(String s) {
        super(s);
    }
}
