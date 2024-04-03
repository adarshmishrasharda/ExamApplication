import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrls: ['./view-quiz-questions.component.css']
})
export class ViewQuizQuestionsComponent {

  qid:any;
  title:any;
  questions:any[] | undefined;
  constructor(private _route:ActivatedRoute,
    private _questionService:QuestionService){
  }

  ngOnInit():void{
    this.qid=this._route.snapshot.params['qid'];
    this.title=this._route.snapshot.params['title'];
    console.log(this.qid);
    console.log(this.title);
    this._questionService.getQuestiionOfQuiz(this.qid).subscribe(
      (data:any)=>{
        //success
        console.log(data);
        this.questions=data;
      },
      (error)=>{

        console.log(error);
      }
    )

  }

  //delete question 
  deleteQuestion(queid:any){
    Swal.fire(
      {
        icon:"info",
        showCancelButton:true,
        confirmButtonText:"Delete",
        title:"Do you want to Delete Question",
      }).then((result)=>{
        if(result.isConfirmed)
        {
          //on delet button press 
          this._questionService.deleteQuestion(queid).subscribe(
            (data)=>{
              Swal.fire("Success !!","Question Deleted Successfully","success");
           
              this.questions=this.questions?.filter((q)=>q.queId!=queid);
            },
            (error)=>{
              Swal.fire("Error","Error in Deleting Question","error");
            }
          )
        }
      });
  }

}
