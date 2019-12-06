package com.raccoona.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CryptoTransferServiceTest {

    @Autowired
    private CryptoTransferService cryptoTransferService;

    @Ignore
    @Test
    public void test() {
        for (int i = 0; i < 5; i++) {
            String txid = cryptoTransferService.transfer("2NE6GNNWKVrZ7SPLZvs3Eu7Dz4fa7pqszjx", BigDecimal.valueOf(0.0001));
            System.out.println(txid);
        }
    }


}