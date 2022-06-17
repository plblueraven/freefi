package pl.ksa.freefi.ssn;

public class SsnFromUnsupportedCountryException extends SsnValidationException {
    public SsnFromUnsupportedCountryException(String message) {
        super(message);
    }
}
