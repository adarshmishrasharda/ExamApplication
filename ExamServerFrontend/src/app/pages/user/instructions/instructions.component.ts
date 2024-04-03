import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent {

  qid:any;

  quiz:any;
  constructor(private _routeService:ActivatedRoute,
    private _quizService:QuizService,
    private _router:Router){

  }

  ngOnInit():void{
    this.qid=this._routeService.snapshot.params['qid'];
    console.log(this.qid);

    this._quizService.getSingleQuiz(this.qid).subscribe(
      (data)=>{

        this.quiz=data;
       // console.log(data);
      },
      (error)=>{
        //console.log(error);
        alert("error in loading quiz data");

      }

    )
  }

  startQuiz(){

    Swal.fire({
      title: "Do you want to start the quiz?",
      showCancelButton: true,
      confirmButtonText: "Start",
      
      icon:'info'
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        
        this._router.navigate(['/start/'+this.qid]);
      } else if (result.isDenied) {
        Swal.fire("Changes are not saved", "", "info");
      }
    });
  }



}
