package com.exam.models.exams;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qid;
	private String description;
	private String maxMarks;
	
	private String title;
	
	
	private String numberofQuestion;
	
	private boolean active =false;
	
	//fetch type EAGER mean jab ham quize nikale to category bhi aa jaye
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	
	@OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Question> questions =new HashSet<>();

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	


	public Quiz(Long qid, String description, String maxMarks, String title, String numberofQuestion, boolean active,
			Category category, Set<Question> questions) {
		super();
		this.qid = qid;
		this.description = description;
		this.maxMarks = maxMarks;
		this.title = title;
		this.numberofQuestion = numberofQuestion;
		this.active = active;
		this.category = category;
		this.questions = questions;
	}
	






	public Set<Question> getQuestions() {
		return questions;
	}



	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}



	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getNumberofQuestion() {
		return numberofQuestion;
	}

	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public void setNumberofQuestion(String numberofQuestion) {
		this.numberofQuestion = numberofQuestion;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}






	public String getTitle() {
		return title;
	}






	public void setTitle(String title) {
		this.title = title;
	}






	@Override
	public String toString() {
		return "Quiz [qid=" + qid + ", description=" + description + ", maxMarks=" + maxMarks + ", title=" + title
				+ ", numberofQuestion=" + numberofQuestion + ", active=" + active + ", category=" + category
				+ ", questions=" + questions + "]";
	}
	
	
	
	

}
