package com.raccoona.service;

import com.raccoona.dto.QuizResponse;
import com.raccoona.entity.Quiz;
import com.raccoona.repository.QuizRepository;
import com.raccoona.repository.RedeemRepository;
import com.raccoona.repository.RewardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {

    private QuizRepository quizRepository;
    private RedeemRepository redeemRepository;
    private RewardRepository rewardRepository;

    public QuizService(QuizRepository quizRepository, RedeemRepository redeemRepository, RewardRepository rewardRepository) {
        this.quizRepository = quizRepository;
        this.redeemRepository = redeemRepository;
        this.rewardRepository = rewardRepository;
    }

    public QuizResponse checkAndGetQuiz(String phrase) {
        Optional<Quiz> quizOptional = quizRepository.findByPhrase(phrase);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            return new QuizResponse(quiz.getReward().getCurrency(), quiz.getReward().getAmount());
        } else {
            throw new RuntimeException("cannot find");
        }
    }
}
