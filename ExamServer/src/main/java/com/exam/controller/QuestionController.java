package com.exam.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

import com.exam.models.exams.Question;
import com.exam.models.exams.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;



@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {
	
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	
	//add question api
	
	@PostMapping("/")
	public ResponseEntity<?> add(@RequestBody Question question)
	{
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
	//update question api
	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody Question question)
	{
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}
	
	
	//get all questions of any quiz
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestionsofQuiz(@PathVariable("quizId")  Long quizId)
	{
		
		//get some questions of that quiz
		
		Quiz quiz= this.quizService.getQuiz(quizId);
		
		Set<Question> questions = quiz.getQuestions();
		
		List<Question> list =new ArrayList(questions);
		
		if(list.size()>Integer.parseInt(quiz.getNumberofQuestion()))
		{
			list=list.subList(0, Integer.parseInt(quiz.getNumberofQuestion()+1));
		}
		
		//ye ham isliye kar rahe hi kyu ki hame admin ko answer dikhana hai aur normal user ko nhai
		//jab ham json ignore use kar rahe the tab admin bhi nahi dekh pa raha tha
		//to tab yaha pe normal user ko data send karate time ham us ke question ke answer ko ek ek kar ke null ya blank set kar de rahe hai manully
		
		list.forEach((q)->{
			q.setAnswer("");
		});
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
		
		/*This we wil not using because this will return all the question of that quiz
		 * But we need only some number of question that is already defined in quiz
		 * 
		 * Quiz quiz =new Quiz();
		quiz.setQid(quizId);
		
		Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
		
		return ResponseEntity.ok(questionsOfQuiz);*/
	}
	
	
	//get all question
	
	@GetMapping("/quiz/all/{quizId}")
	public ResponseEntity<?> getAllQuestionsofQuizAdmin(@PathVariable("quizId")  Long quizId)
	{
		
		//get some questions of that quiz
		Quiz quiz =new Quiz();
		quiz.setQid(quizId);
		
		Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
		
		return ResponseEntity.ok(questionsOfQuiz);
		//return ResponseEntity.ok(list);
		
		/*This we wil not using because this will return all the question of that quiz
		 * But we need only some number of question that is already defined in quiz
		 * 
		 * Quiz quiz =new Quiz();
		quiz.setQid(quizId);
		
		Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
		
		return ResponseEntity.ok(questionsOfQuiz);*/
	}
	
	
	
	
	//get single question
	@GetMapping("/{quesId}")
	public Question getSingleQuestion(@PathVariable("quesId") Long quesId )
	{
		return this.questionService.getQuestion(quesId);
	}
	
	
	//delete question
	
	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId)
	{
		this.questionService.deleteQuestion(quesId);
	}
	
	//evaluate quiz
	
	@PostMapping("/eval-quiz")
	public  ResponseEntity<?> evaluateQuiz(@RequestBody List<Question> question) {
		System.out.println("question is "+question);
		
		  Double marksGot=0.0;
		  Integer correctAnswers=0;
		  Integer attempted=0;
		 
		 for(Question q:question){
			//single questions
			Question question1 = this.questionService.get(q.getQueId());
			if(question1.getAnswer().trim().equals(q.getGivenAnswer())) {
				//correct hai
				correctAnswers++;
				double marksSingle=Double.parseDouble(question.get(0).getQuiz().getMaxMarks())/question.size();
			
				marksGot+=marksSingle;
				
			}
			if(q.getGivenAnswer()!=null) {
				
				attempted++;
			}
			System.out.println("question is "+q.getGivenAnswer());
		}
		 
		 Map<String, Object> map=Map.of("marksGot",marksGot,"correctAnswers",correctAnswers
				 ,"attempted",attempted);
		
		return ResponseEntity.ok(map);	
		
	}
	
		
		
	
	

}
