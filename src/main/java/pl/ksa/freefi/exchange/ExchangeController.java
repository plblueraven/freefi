package pl.ksa.freefi.exchange;

import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

import static pl.ksa.freefi.exchange.CacheConfig.RATES_CACHE;

@RestController
public class ExchangeController {
    private final CacheManager cacheManager;

    public ExchangeController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @GetMapping("exchange_amount")
    public ExchangeResponse validateSsn( @RequestParam String from, @RequestParam String to, @RequestParam("from_amount") @Positive BigDecimal fromAmount) {
        final Exchange exchange = new Exchange(from, to);
        final BigDecimal exchangeRate = cacheManager.getCache(RATES_CACHE).get(exchange, BigDecimal.class);
        return new ExchangeResponse(from, to, fromAmount.multiply(exchangeRate), exchangeRate);
    }
}
