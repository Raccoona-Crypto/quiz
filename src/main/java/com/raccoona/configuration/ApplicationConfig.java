package com.raccoona.configuration;

import com.coinselection.CoinSelectionFactory;
import com.coinselection.CoinSelectionProvider;
import com.coinselection.model.Algorithm;
import com.coinselection.size.LegacySizeProvider;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            request.getHeaders().add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            return execution.execute(request, body);
        };
        return new RestTemplateBuilder().additionalInterceptors(interceptor).build();
    }

    @Bean
    public CoinSelectionProvider coinSelectionProvider() {
        return CoinSelectionFactory.INSTANCE.create(Algorithm.RANDOM_IMPROVE, 60, LegacySizeProvider.INSTANCE.provide());
    }

    @Bean
    public NetworkParameters networkParameters(@Value("${mainnet}") Boolean mainnet) {
        if (mainnet) {
            return MainNetParams.get();
        } else {
            return TestNet3Params.get();
        }
    }
}
