package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.exam.Category;
import com.app.model.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	public List<Quiz> findBycategory(Category category);

}
