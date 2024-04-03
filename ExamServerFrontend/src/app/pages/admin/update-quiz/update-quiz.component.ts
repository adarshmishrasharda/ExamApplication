import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css']
})
export class UpdateQuizComponent {

  constructor(private _routeActivated:ActivatedRoute,
    private _quizService:QuizService,
    private _categoryService:CategoryService,
    private _router:Router){

  }

  qid=0;
  quizData: any;
  categories:any;

  ngOnInit()
  {
    this.qid=this._routeActivated.snapshot.params['qid'];
    //alert(this.qid);
    this._quizService.getSingleQuiz(this.qid).subscribe(
      (data)=>{
        //success
        this.quizData=data;
        console.log(this.quizData);

      },
      (error)=>
      {
        console.log(error);


      }
    )
    this._categoryService.categories().subscribe(
      (data)=>{
        this.categories=data;
      },
      (error)=>{
        alert('Error in loading categories');
      }
    )

  }

  //update 
  public updateData(){
    //validate

    this._quizService.updateQuiz(this.quizData).subscribe(
      (data)=>{
        Swal.fire("Updated","Data get updated Successfully","success").then(
          (e)=>{
            this._router.navigate(['/admin/quizzes']);
          }
        );


      },
      (error)=>{
        Swal.fire("Error","Error in updating quiz","error")

      }
    )
    console.log("going to update data",this.updateData);
  }

}
