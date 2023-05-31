package br.com.erudio.springproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -3948598267470540665L;

    public UnsupportedMathOperationException(String s) {
        super(s);
    }
}
