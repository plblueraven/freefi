package pl.ksa.freefi.exchange;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ExchangeController {

    @GetMapping("exchange_amount")
    public ExchangeResponse validateSsn(ExchangeRequest request) {
        return new ExchangeResponse("EUR", "USD", BigDecimal.valueOf(1.1), BigDecimal.valueOf(1.1));
    }
}
