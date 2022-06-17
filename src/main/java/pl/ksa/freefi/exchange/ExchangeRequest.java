package pl.ksa.freefi.exchange;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

record ExchangeRequest(@NotBlank String from, @NotBlank String to, @NotNull @Positive BigDecimal fromAmount) {
}
