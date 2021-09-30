package com.app.service;

import java.util.Set;

import com.app.model.exam.Question;
import com.app.model.exam.Quiz;

public interface QuestionService {
	
	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestions();
	
	public Question getQuestion(Long questionId);
	
	public Set<Question> getQuestionOfQuiz(Quiz quiz);
	
	public void deleteQuestion(Long questionId);
	
	

}
