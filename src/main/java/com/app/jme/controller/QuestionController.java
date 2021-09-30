package com.app.jme.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

import com.app.jme.model.exam.Question;
import com.app.jme.model.exam.Quiz;
import com.app.jme.service.QuestionService;
import com.app.jme.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	// add question
	@PostMapping("/")
	public ResponseEntity<Question> add(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}

	// updateQuestion
	@PutMapping("/")
	public ResponseEntity<Question> update(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}

	// get single Question
	@GetMapping("/{quesId}")
	public Question question(@PathVariable("quesId") Long quesId) {
		return this.questionService.getQuestion(quesId);
	}

	// get random question for quiz... for user decided by admin
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid) {

		Quiz quiz = this.quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();
		List list = new ArrayList(questions);
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}

	// get all question of any quiz ... for admin
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("qid") Long qid) {
		Quiz quiz = new Quiz();
		quiz.setqId(qid);
		Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
		return ResponseEntity.ok(questionOfQuiz);
	}

	// delete a question
	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId) {
		this.questionService.deleteQuestion(quesId);
	}

}
