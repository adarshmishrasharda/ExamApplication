import { Component } from '@angular/core';


import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent {

  quizzes=[
    {
      qid:23,
      title:'Basiz Java Quiz',
      description:'quiz description ',
      maxMarks:'50',
      numberofQuestion:'20',
      active:'',
      category:{
        title:'Programming'
      }

    },
    {
      qid:23,
      title:'Basiz Java Quiz',
      description:'quiz description',
      maxMarks:'50',
      numberOfQuestion:'20',
      active:'',
      category:{
        title:'Programming'
      }

    },
  ];

  constructor(private quizservice:QuizService){}

  ngOnInit():void{
    this.quizservice.quizzes().subscribe(
      (data:any)=>{
        this.quizzes=data;
        console.log(this.quizzes);
      },
      (error)=>{

        console.log(error);
        Swal.fire("Error !","Error in Loading Data","error");
        
      }
    )
  }

  deleteQuiz(qid:any){

    Swal.fire({
      icon:'info',
      title:"Are you Sure?",
      confirmButtonText:'Delete',
      showCancelButton:true,
    }).then((result)=>{
      if(result.isConfirmed)
      {
        //delect karana chah rahe hai
        this.quizservice.deleteQuiz(qid).subscribe(
          (data)=>{
    
    
            this.quizservice.quizzes().subscribe(
              (data:any)=>{
                this.quizzes=data;
                console.log(this.quizzes);
              },
              (error)=>{
        
                console.log(error);
                Swal.fire("Error !","Error in Loading Data","error");
                
              }
            )
    
            // this.quizzes=this.quizzes.filter((quiz)=>{
            //   quiz.qid!=qid;
    
            // });
            Swal.fire("success","successfully deleted quiz","success");
          },
          (error)=>
          {
            Swal.fire("Error","Some error Occur while Deleting","error");
          }
    
        )
      }
    });

  
    
  }

}
