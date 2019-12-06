package com.raccoona.service;

import com.raccoona.dto.RawTransactionDto;
import com.raccoona.dto.ResultDto;
import com.raccoona.dto.ResultStringDto;
import com.raccoona.dto.UtxoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BlockbookService {

    private RestTemplate restTemplate;
    private String baseUrl;

    public BlockbookService(RestTemplate restTemplate,
                            @Value("${blockbook.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public Set<UtxoDto> getUtxos(String address) {
        String url = baseUrl + "/api/v2/utxo/" + address;
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("confirmed", true);
        return Arrays.asList(restTemplate.getForObject(
                builder.toUriString(), UtxoDto[].class)).stream().collect(Collectors.toSet());
    }

    //    0.0002011
    public BigDecimal estimateFee(int blocks) {
        String url = baseUrl + "/api/v2/estimatefee/" + blocks;
        return restTemplate.getForObject(
                url, ResultDto.class).result;
    }

    public String postTransaction(String hex) {
        String url = baseUrl + "/api/v2/sendtx/" + hex;
        return restTemplate.getForObject(
                url, ResultStringDto.class).result;
    }

    public RawTransactionDto getRawTransaction(String txid) {
        String url = baseUrl + "/api/v2/tx/" + txid;
        return restTemplate.getForObject(
                url, RawTransactionDto.class);
    }

}
