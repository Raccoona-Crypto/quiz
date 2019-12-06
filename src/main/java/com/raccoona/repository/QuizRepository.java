package com.raccoona.repository;

import com.raccoona.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
    Optional<Quiz> findByPhrase(String phrase);
}
