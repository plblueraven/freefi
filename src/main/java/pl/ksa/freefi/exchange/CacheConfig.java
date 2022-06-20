package pl.ksa.freefi.exchange;

import org.cache2k.extra.spring.SpringCache2kCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.ksa.freefi.exchange.integration.ApilayerService;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    public static final String RATES_CACHE = "rates";

    // As 1 hour refresh indicates that we don't need so much precision therefore cache2k default mechanisms is enough
    // also it is affordable that few clients per hour could wait longer for response
    @Bean
    public CacheManager cacheManager(ApilayerService apilayerService) {
        return new SpringCache2kCacheManager().addCaches(
                b -> b.name(RATES_CACHE).expireAfterWrite(1, TimeUnit.HOURS).entryCapacity(1).refreshAhead(true)
                        .permitNullValues(false)
                        .keyType(Exchange.class).valueType(BigDecimal.class)
                        .loader(key -> apilayerService.getRate(key).info().rate())
        );
    }
}
