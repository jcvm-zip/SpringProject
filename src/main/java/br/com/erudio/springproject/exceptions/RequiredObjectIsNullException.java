package br.com.erudio.springproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -3948598267470540665L;

    public RequiredObjectIsNullException() {
        super("It is not allowed to persist a null object");
    }

    public RequiredObjectIsNullException(String s) {
        super(s);
    }
}
