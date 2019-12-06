package com.raccoona.controller;

import com.raccoona.dto.*;
import com.raccoona.service.CryptoTransferService;
import com.raccoona.service.QuizService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class QuizController {

    private QuizService quizService;

    private CryptoTransferService cryptoTransferService;

    public QuizController(QuizService quizService, CryptoTransferService cryptoTransferService) {
        this.quizService = quizService;
        this.cryptoTransferService = cryptoTransferService;
    }

    @PostMapping("/quiz/check")
    QuizResponse quizExists(@Valid @RequestBody QuizRequest quizRequest) {
        return quizService.checkAndGetQuiz(quizRequest.getQuizPhrase());
    }

    @PostMapping("/quiz/redeem")
    QuizRedeemResponse redeemQuiz(@Valid @RequestBody QuizRedeemRequest quizRedeemRequest) {
        System.out.println(quizRedeemRequest);
        QuizResponse quizResponse = quizService.checkAndGetQuiz(quizRedeemRequest.getQuizPhrase());

        String txid = cryptoTransferService.transfer(quizRedeemRequest.getAddress(), quizResponse.getAmount());
        System.out.println(txid);
        return new QuizRedeemResponse(quizResponse.getCurrency(), txid);
    }

}
