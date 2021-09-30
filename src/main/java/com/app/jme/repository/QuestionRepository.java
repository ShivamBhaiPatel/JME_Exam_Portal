package com.app.jme.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jme.model.exam.Question;
import com.app.jme.model.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Set<Question> findByQuiz(Quiz quiz);

}
