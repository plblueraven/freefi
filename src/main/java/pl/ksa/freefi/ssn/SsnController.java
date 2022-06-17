package pl.ksa.freefi.ssn;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class SsnController {

    private final SsnValidator ssnValidator;

    public SsnController(SsnValidator ssnValidator) {
        this.ssnValidator = ssnValidator;
    }

    @PostMapping("validate_ssn")
    public SsnValidationResponse validateSsn(@RequestParam(value = "country_code", defaultValue = "FI") String countryCode, @RequestBody @Valid SsnHolder holder) {
        return new SsnValidationResponse(ssnValidator.isValid(countryCode, holder.ssn()));
    }
}
