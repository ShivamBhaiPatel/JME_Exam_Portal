package com.app.jme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.jme.model.exam.Category;
import com.app.jme.model.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	public List<Quiz> findBycategory(Category category);

}
