package com.raccoona.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class QuizRedeemRequest {
    //    (\b[a-z0-9]+\b\s?){4,}
    @NotBlank
    @Pattern(regexp = "(\\b[a-z0-9]+\\b\\s?){4,}", message = "invalid phrase format")
    private String quizPhrase;

    private String address;

    public String getQuizPhrase() {
        return quizPhrase;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "QuizRedeemRequest{" +
                "quizPhrase='" + quizPhrase + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}


