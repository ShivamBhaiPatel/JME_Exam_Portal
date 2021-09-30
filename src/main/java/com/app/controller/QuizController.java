package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.exam.Category;
import com.app.model.exam.Quiz;
import com.app.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	// add quiz service
	@PostMapping("/")
	ResponseEntity<Quiz> add(@RequestBody Quiz Quiz ) {
		return ResponseEntity.ok(this.quizService.addQuiz(Quiz));
	}
	
	//update quiz
	@PutMapping("/")
	ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.update(quiz));
	}
	
	// get all quizzes
	@GetMapping("/")
	public ResponseEntity<?> quizes() {
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	
	//get single quiz
	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") Long qid) {
		return this.quizService.getQuiz(qid);
	}
	
	
	//delete the quiz
	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") Long qid)
	{
		this.quizService.deleteQuiz(qid);
	}
	
	//
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable("/cid") Long cid) {
		Category category = new Category();
		category.setcId(cid);
		return this.quizService.getQuizzesOfCategory(category);
	}
	

}
