package pl.ksa.freefi.ssn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SsnValidationExceptionHandler {

    @ExceptionHandler(SsnFromUnsupportedCountryException.class)
    protected ResponseEntity<Object> handleSsnFromUnsupportedCountry(SsnFromUnsupportedCountryException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RestException(ex.getMessage()));
    }

    record RestException(String message){}
}
