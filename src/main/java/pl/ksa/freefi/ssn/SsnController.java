package pl.ksa.freefi.ssn;

import org.springframework.web.bind.annotation.*;

@RestController
public class SsnController {
    @PostMapping("validate_ssn")
    public SsnValidationResponse validateSsn(@RequestBody SsnHolder holder, @RequestParam(value = "country_code", defaultValue = "FI") String countryCode) {
        return new SsnValidationResponse(false);
    }
}
