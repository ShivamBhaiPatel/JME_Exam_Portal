package com.app.service;

import java.util.List;
import java.util.Set;

import com.app.model.exam.Category;
import com.app.model.exam.Quiz;

public interface QuizService {
	
	public Quiz addQuiz(Quiz quiz);
	
	public Quiz update(Quiz quiz);
	
	public Set<Quiz> getQuizzes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);

	public List<Quiz> getQuizzesOfCategory(Category category);


}
