package com.raccoona.configuration;

import com.coinselection.CoinSelectionFactory;
import com.coinselection.CoinSelectionProvider;
import com.coinselection.model.Algorithm;
import com.coinselection.size.LegacySizeProvider;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
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
