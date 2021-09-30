package com.app.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.exam.Question;
import com.app.model.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Set<Question> findByQuiz(Quiz quiz);

}
