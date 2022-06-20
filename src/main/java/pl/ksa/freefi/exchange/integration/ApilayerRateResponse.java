package pl.ksa.freefi.exchange.integration;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public record ApilayerRateResponse(boolean success, @NotNull RateInfo info) {
    public record RateInfo(@NotNull @Positive BigDecimal rate) {
    }
}
