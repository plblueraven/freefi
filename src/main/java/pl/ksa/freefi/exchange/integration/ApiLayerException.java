package pl.ksa.freefi.exchange.integration;

public class ApiLayerException extends RuntimeException {
    public ApiLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
