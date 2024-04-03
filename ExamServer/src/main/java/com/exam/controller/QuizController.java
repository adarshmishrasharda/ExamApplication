package com.exam.controller;

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

import com.exam.models.exams.Category;
import com.exam.models.exams.Quiz;
import com.exam.service.QuizService;


@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
	
	
	@Autowired
	private QuizService quizService;
	
	
	//add quiz api
	@PostMapping("/")
	public ResponseEntity<Quiz> add(@RequestBody Quiz quiz)
	{
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	
	}
	
	
	//update quiz api
	@PutMapping("/")
	public ResponseEntity<Quiz> update(@RequestBody Quiz quiz)
	{
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	
	//get all quiz api
	@GetMapping("/")
	public ResponseEntity<?> quizzes()
	{
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	
	//get single quiz	
	
	@GetMapping("/{qid}")
	public Quiz quiz(@PathVariable("qid") Long qid) {
		return this.quizService.getQuiz(qid);
	}
	
	//delete quiz
	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") Long qid ){
		this.quizService.deleteQuiz(qid);
		
		
	}
	
	//get category wise quiz

	@GetMapping("/category/{cid}")
	public List<Quiz> getCategoryWiseQuiz(@PathVariable("cid") Long cid)
	{
		Category category=new Category();
		category.setCid(cid);
		return this.quizService.getQuizzesCategoryWise(category);
	}
	
	//get active quizzes
	
	@GetMapping("/active")
	public List<Quiz> getActiveQuiz(){
		return this.quizService.getActiveQuizzes();
	}
		
	
	//get active quizzes
	
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizCategory(@PathVariable("cid") Long cid){
		
		Category category=new Category();
		
		category.setCid(cid);
		
		return this.quizService.getActiveQuizzesOfCategory(category);
	}
	
}
