import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent {

  categories=[
    {
      cid:23,
      title:'Programming',
    },
    {
      cid:24,
      title:'Science',
    }
  ];

  //ham ye form ke data ko bind karane ke liye matlab jab submit kare tab 
  //data le paaye us ke liye use kar rahe hai but ye 
  quizData={
    title:'',
    description:'',
    maxMarks:'',
    numberofQuestion:'',
    active:true,
    category:{
      cid:''
    }

  }


  constructor(private _category:CategoryService,
    private _snack:MatSnackBar,
    private _quizService:QuizService){

  }

  ngOnInit():void{

    this._category.categories().subscribe(
      (data:any)=>{
        //categories load success
        this.categories=data;
        //console.log(data);

      },
      (error)=>{
        //console.log(error);
        Swal.fire("Error! ","Error in Loading Data from server","error");

      }
    )

  }

  //add quiz
  public addQuiz(){
    if(this.quizData.title.trim()=='' || this.quizData.title==null)
    {
       this._snack.open("Title Required !!","",{
        duration:3000,
      });
      return;

    }
    if(this.quizData.maxMarks.trim()=='' || this.quizData.maxMarks==null)
    {
       this._snack.open("Maximum Marks is Required !!","",{
        duration:3000,
      });
      return;
      
    }
    if(this.quizData.numberofQuestion.trim()=='' || this.quizData.numberofQuestion==null)
    {
       this._snack.open("Number Of Question is Required !!","",{
        duration:3000,
      });
      return;
      
    }

    //call server

    this._quizService.addQuizzes(this.quizData).subscribe(
      (data)=>{
        Swal.fire("Success","Quiz is Added",'success');
        this.quizData={
          title:'',
          description:'',
          maxMarks:'',
          numberofQuestion:'',
          active:true,
          category:{
            cid:''
          }
      
        };
      
      },
      (error)=>{
        Swal.fire("Error","Error while Adding ,Quiz not added","error");
      }
    );

  }

  

}
