package com.raccoona.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class QuizRequest {
//    (\b[a-z0-9]+\b\s?){4,}
    @NotBlank
    @Pattern(regexp = "(\\b[a-z0-9]+\\b\\s?){4,}", message = "invalid phrase format")
    private String quizPhrase;

    public String getQuizPhrase() {
        return quizPhrase;
    }
}
