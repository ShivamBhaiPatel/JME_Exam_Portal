package com.app.model.exam;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qId;

	private String title;
	
	private String description;
	
	private Double maxMarks;

	private String numberOfQuestions;
	
	private boolean active=false;
	
	// add category
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	
	@OneToMany(mappedBy = "quiz", /* fetch = FetchType.LAZY,*/ cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Question> question =new HashSet<>();


	
	
	public Quiz() {
	}


	public long getqId() {
		return qId;
	}


	public void setqId(long qId) {
		this.qId = qId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getMaxMarks() {
		return maxMarks;
	}


	public void setMaxMarks(Double maxMarks) {
		this.maxMarks = maxMarks;
	}


	public String getNumberOfQuestions() {
		return numberOfQuestions;
	}


	public void setNumberOfQuestions(String numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Set<Question> getQuestion() {
		return getQuestion();
	}


	public void setQuestion(Set<Question> question) {
		this.question = question;
	}
	public Set<Question> getQuestions() {
		return question;
	}


	public void setQuestions(Set<Question> question) {
		this.question = question;
	}


	public void setqId(Long qId) {
		this.qId = qId;
	}
	
	
}
