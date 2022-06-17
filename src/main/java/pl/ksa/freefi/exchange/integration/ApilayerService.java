package pl.ksa.freefi.exchange.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import pl.ksa.freefi.exchange.Exchange;

@Service
public class ApilayerService {
    private final RestTemplate restTemplate;

    public ApilayerService(@Value("${freefi.exchange.integration.api-key}") String apiKey) {
        this.restTemplate = new RestTemplateBuilder().uriTemplateHandler(new DefaultUriBuilderFactory("https://api.apilayer.com/exchangerates_data/"))
                .defaultHeader("apikey", apiKey).build();
    }

    public ApilayerRateResponse getRate(Exchange exchange) {
        return restTemplate.getForObject("convert?from={from}&to={to}&amount=1", ApilayerRateResponse.class, exchange.from(), exchange.to());
    }
}
