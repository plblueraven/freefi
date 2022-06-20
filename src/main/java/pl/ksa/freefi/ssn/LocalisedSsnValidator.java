package pl.ksa.freefi.ssn;

interface LocalisedSsnValidator {
    String getSupportedCountryCode();

    boolean isValid(String ssn);
}
