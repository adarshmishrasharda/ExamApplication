import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';


@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css']
})
export class LoadQuizComponent {

  catId:any;
  quizzes:any;

  constructor(private _route:ActivatedRoute,private _quizService:QuizService){

  }

  ngOnInit():void{


    this._route.params.subscribe(
      (params)=>{
        //console.log(params);
        this.catId=params['catId'];
        if(this.catId==0)
        {
          console.log("Laod all the quiz");
          this._quizService.getActiveQuizzes().subscribe(
            (data)=>{
              this.quizzes=data;
              console.log("quiz d all data"+this.quizzes);
    
    
            },
            (error)=>{
              console.log("error in loading all quizes");
              alert("error in loading all quizes");
    
            }
          )
    
    
        }
        else{
          console.log("load specific quiz");
          this._quizService.getActiveQuizzesOfCategroy(this.catId).subscribe(
            (data)=>{
              this.quizzes=data;

            },
            (error)=>{

              alert("error in loading quiz data");
            }
          )
        }

      }
    )


    
   
  }

}
