package com.raccoona.service;

import com.raccoona.dto.QuizRedeemRequest;
import com.raccoona.dto.QuizRedeemResponse;
import com.raccoona.dto.QuizResponse;
import com.raccoona.dto.QuizResponseFull;
import com.raccoona.entity.Quiz;
import com.raccoona.entity.Redeem;
import com.raccoona.exception.AlreadyExistsException;
import com.raccoona.exception.NotFoundException;
import com.raccoona.repository.QuizRepository;
import com.raccoona.repository.RedeemRepository;
import com.raccoona.repository.RewardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuizService {

    Logger logger = LogManager.getLogger(QuizService.class);

    private QuizRepository quizRepository;
    private RedeemRepository redeemRepository;
    private RewardRepository rewardRepository;

    private CryptoTransferService cryptoTransferService;

    public QuizService(QuizRepository quizRepository, RedeemRepository redeemRepository, RewardRepository rewardRepository, CryptoTransferService cryptoTransferService) {
        this.quizRepository = quizRepository;
        this.redeemRepository = redeemRepository;
        this.rewardRepository = rewardRepository;
        this.cryptoTransferService = cryptoTransferService;
    }

    public QuizResponse checkAndGetQuiz(String phrase) {
        QuizResponseFull quizResponseFull = checkAndGetQuizFull(phrase);
        return new QuizResponse(quizResponseFull.getCurrency(), quizResponseFull.getAmount());
    }

    public QuizRedeemResponse redeemQuiz(QuizRedeemRequest quizRedeemRequest) {
        logger.info(String.format("Redeeming quiz: %s", quizRedeemRequest));
        QuizResponseFull quizResponseFull = checkAndGetQuizFull(quizRedeemRequest.getQuizPhrase());

        if (quizResponseFull.getRedeem() != null) {
            throw new AlreadyExistsException(String.format("Quiz already redeemed: txid %s", quizResponseFull.getRedeem().getTxid()));
        }
        String txid = cryptoTransferService.transfer(quizRedeemRequest.getAddress(), quizResponseFull.getAmount());
        logger.info(String.format("Published transaction with txid: %s", txid));

        Redeem redeem = new Redeem();
        redeem.setAddress(quizRedeemRequest.getAddress());
        redeem.setAmount(quizResponseFull.getAmount());
        redeem.setCurrency(quizResponseFull.getCurrency());
        redeem.setTxid(txid);

        saveRedeem(quizRedeemRequest, redeem);

        return new QuizRedeemResponse(quizResponseFull.getCurrency(), txid);
    }

    private void saveRedeem(QuizRedeemRequest quizRedeemRequest, Redeem redeem) {
        Optional<Quiz> quizOptional = quizRepository.findByPhrase(quizRedeemRequest.getQuizPhrase());
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            quiz.setRedeem(redeem);
        }

        redeemRepository.save(redeem);
    }

    private QuizResponseFull checkAndGetQuizFull(String phrase) {
        Optional<Quiz> quizOptional = quizRepository.findByPhrase(phrase);
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            return new QuizResponseFull(
                    quiz.getReward().getCurrency(),
                    quiz.getReward().getAmount(),
                    quiz.getStatus(),
                    quiz.getRedeem(),
                    quiz.getReward());
        } else {
            throw new NotFoundException(String.format("cannot find quiz with phrase %s", phrase));
        }
    }
}
