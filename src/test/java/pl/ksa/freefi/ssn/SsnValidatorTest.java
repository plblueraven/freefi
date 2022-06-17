package pl.ksa.freefi.ssn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SsnValidatorTest {
    private static final String SUPPORTED_COUNTRY_CODE = "FI";
    private static final String UNSUPPORTED_COUNTRY_CODE = "NON_EXISTING_COUNTRY_CODE";
    private static final String SSN_FROM_INSTRUCTION = "131052-308T";
    private SsnValidator ssnValidator = new SsnValidator();

    @Test
    void ssnFromInstruction_shouldBeValid(){
        assertTrue(ssnValidator.isValid(SUPPORTED_COUNTRY_CODE, SSN_FROM_INSTRUCTION), "According to instruction %s should be valid".formatted(SSN_FROM_INSTRUCTION));
    }

    @Test
    void ssnsFromUnsupportedCounty_shouldNotBeAccepted(){
        assertThrows(SsnFromUnsupportedCountryException.class, () -> ssnValidator.isValid(UNSUPPORTED_COUNTRY_CODE, SSN_FROM_INSTRUCTION), () -> "SSNs from %s should not be accepted".formatted(UNSUPPORTED_COUNTRY_CODE));
    }

}
