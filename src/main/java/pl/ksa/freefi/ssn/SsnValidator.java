package pl.ksa.freefi.ssn;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SsnValidator {
    private final Map<String, LocalisedSsnValidator> validators;

    public SsnValidator(Set<LocalisedSsnValidator> validatorImplementations) {
        validators = validatorImplementations.stream().collect(Collectors.toMap(LocalisedSsnValidator::getSupportedCountryCode, Function.identity()));
    }

    public boolean isValid(String countryCode, String ssn) {
        final LocalisedSsnValidator validator = validators.get(countryCode);
        if (validator == null) {
            throw new SsnFromUnsupportedCountryException("There is no proper validator for county code %s. You could try one of: %s"
                    .formatted(countryCode, String.join(", ", validators.keySet())));
        }
        return validator.isValid(ssn);
    }
}
