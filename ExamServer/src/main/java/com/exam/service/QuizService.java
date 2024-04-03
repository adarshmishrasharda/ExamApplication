package com.exam.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.models.exams.Category;
import com.exam.models.exams.Quiz;

@Service
public interface QuizService {
	
	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	 public Set<Quiz> getQuizzes();
	 
	 
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);

	public List<Quiz> getQuizzesCategoryWise(Category category);

	public List<Quiz> getActiveQuizzes();
	
	public List<Quiz> getActiveQuizzesOfCategory(Category c);
	
}
