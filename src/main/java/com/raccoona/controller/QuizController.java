package com.raccoona.controller;

import com.raccoona.dto.QuizRedeemRequest;
import com.raccoona.dto.QuizRedeemResponse;
import com.raccoona.dto.QuizRequest;
import com.raccoona.dto.QuizResponse;
import com.raccoona.service.QuizService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quiz/check")
    QuizResponse quizExists(@Valid @RequestBody QuizRequest quizRequest) {
        return quizService.checkAndGetQuiz(quizRequest.getQuizPhrase());
    }

    @PostMapping("/quiz/redeem")
    QuizRedeemResponse redeemQuiz(@Valid @RequestBody QuizRedeemRequest quizRedeemRequest) {
        return quizService.redeemQuiz(quizRedeemRequest);
    }

}
