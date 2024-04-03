import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private _http:HttpClient) { }

  //get all quizes;

  public quizzes()
  {
    return this._http.get(`${baseUrl}/quiz/`)
  }
  //add quiz
  public addQuizzes(quiz: any){
    return this._http.post(`${baseUrl}/quiz/`,quiz);
  }

  //delect quiz
  public deleteQuiz(qid:any)
  {
    return this._http.delete(`${baseUrl}/quiz/${qid}`);

  }
  //get the single quiz by id

  public getSingleQuiz(qid:any)
  {
    return this._http.get(`${baseUrl}/quiz/${qid}`);
  }
  //update quiz
  public updateQuiz(quiz:any)
  {
    return this._http.put(`${baseUrl}/quiz/`,quiz);
  }
  //get quizes of category
  public getQuizzesOfCategory(cid:any)
  {
    return this._http.get(`${baseUrl}/quiz/category/${cid}`);

  } 
  //get active quizes
  public getActiveQuizzes()
  {
    return this._http.get(`${baseUrl}/quiz/active`);

  } 

  //get active quizesof category
  public getActiveQuizzesOfCategroy(cid:any)
  {
    return this._http.get(`${baseUrl}/quiz/category/active/${cid}`);

  }
}
