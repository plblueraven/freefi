package pl.ksa.freefi.exchange;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.ksa.freefi.exchange.integration.ApiLayerException;

@ControllerAdvice
public class ExchangeRateExceptionHandler {

    @ExceptionHandler(ApiLayerException.class)
    protected ResponseEntity<Object> handleApiLayerException(ApiLayerException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RestException(ex.getMessage()));
    }

    record RestException(String message){}
}
