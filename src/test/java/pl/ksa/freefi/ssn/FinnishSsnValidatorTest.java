package pl.ksa.freefi.ssn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FinnishSsnValidatorTest {
    private static final String SSN_FROM_INSTRUCTION = "131052-308T";
    private static final String SSN_FROM_FAKE_NAME_GENERATOR = "111108A726E";
    private final FinnishSsnValidator finnishSsnValidator = new FinnishSsnValidator();

    @Test
    void ssnFromInstruction_shouldBeValid() {
        assertTrue(finnishSsnValidator.isValid(SSN_FROM_INSTRUCTION), "According to instruction %s should be valid".formatted(SSN_FROM_INSTRUCTION));
    }

    @Test
    void ssnFromFakeNameGenerator_shouldBeValid() {
        assertTrue(finnishSsnValidator.isValid(SSN_FROM_FAKE_NAME_GENERATOR), "According to https://www.fakenamegenerator.com/advanced.php?t=country&n[]=fi&c[]=fi %s should be valid".formatted(SSN_FROM_INSTRUCTION));
    }

    @Test
    void emptyString_shouldInvalid() {
        assertFalse(finnishSsnValidator.isValid(""), "Empty string should be threaded as invalid ssn");
    }

    @Test
    void ssnWithToLessCharacters_shouldInvalid() {
        assertFalse(finnishSsnValidator.isValid(SSN_FROM_INSTRUCTION.substring(1)), "Empty string should be threaded as invalid ssn");
    }

    @Test
    void ssnWithToMuchCharacters_shouldInvalid() {
        assertFalse(finnishSsnValidator.isValid(SSN_FROM_INSTRUCTION + "1"), "Empty string should be threaded as invalid ssn");
    }
}
