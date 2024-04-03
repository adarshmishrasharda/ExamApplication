package com.exam.models.exams;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long queId;
	
	
	private String content;
	 
	private String image;
	
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	//after putting json ignore on this field -- ye field ab json ke formate me nahi jayegi client ko
	
	
	private String answer;
	@Transient
	private String givenAnswer;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;


	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	public Question(Long queId, String content, String image, String option1, String option2, String option3,
			String option4, String answer, String givenAnswer, Quiz quiz) {
		super();
		this.queId = queId;
		this.content = content;
		this.image = image;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
		this.givenAnswer = givenAnswer;
		this.quiz = quiz;
	}





	public Long getQueId() {
		return queId;
	}


	public void setQueId(Long queId) {
		this.queId = queId;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getOption1() {
		return option1;
	}


	public void setOption1(String option1) {
		this.option1 = option1;
	}


	public String getOption2() {
		return option2;
	}


	public void setOption2(String option2) {
		this.option2 = option2;
	}


	public String getOption3() {
		return option3;
	}


	public void setOption3(String option3) {
		this.option3 = option3;
	}


	public String getOption4() {
		return option4;
	}


	public void setOption4(String option4) {
		this.option4 = option4;
	}

	
	/* jab ham property pe json ignore laga dege tab na to data JSON
	 * ke form me fromtend me jayega na to object se json me connevert ho ke 
	 * backend me aayege to jab data aayega hi nahi to answer save hi nahi hoga
	 * but problem solve karane ke liye
	 * getter pe ham json ignore laga dege to data to JSON se object me ho ke frontend pe nahi jayega 
	 * setter pe ham json propert laga dege to data object se conevert ho ke backend me aayega aur ham save kar lege
	 * 
	 * 
	 * but problem ye hai ki jab us data ko admin hi call karega dekhen ke liye (kyu ki admin to answer dekh 
	 * skata hai to hai) to us ke pass bhi answer nahi jayega kyu ki getter ke json ignore hai 
	 * 
	 * 
	 * to is is hamri problem ka solutio nahi hua to ham ise comment out kar rahe hai*/

	//@JsonIgnore
	public String getAnswer() {
		return answer;
	}

	
	//@JsonProperty("answer")
	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public Quiz getQuiz() {
		return quiz;
	}


	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}





	public String getGivenAnswer() {
		return givenAnswer;
	}





	public void setGivenAnswer(String givenAnswer) {
		this.givenAnswer = givenAnswer;
	}





	@Override
	public String toString() {
		return "Question [queId=" + queId + ", content=" + content + ", image=" + image + ", option1=" + option1
				+ ", option2=" + option2 + ", option3=" + option3 + ", option4=" + option4 + ", answer=" + answer
				+ ", givenAnswer=" + givenAnswer + ", quiz=" + quiz + "]";
	}
	
	
	


}
