import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';



@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent {

  public Editor = ClassicEditor;

  qid:any;
  title:any;
  question={
    quiz:{

      qid:''
    },
    content:'',
    option1:'',
    option2:'',
    option3:'',
    option4:'',
    answer:'',
  };

  constructor(private _route:ActivatedRoute,
    private _question:QuestionService){}

  ngOnInit():void{
    this.qid=this._route.snapshot.params['qid'];
    this.title=this._route.snapshot.params['title'];
    console.log(this.qid);
    this.question.quiz['qid']=this.qid;
  }

  formSubmit(){
    if(this.question.content.trim()=='' || this.question.content==null)
    {
      return
    }
    if(this.question.option1.trim()=='' || this.question.option1==null)
    {
      return
    }
    if(this.question.option2.trim()=='' || this.question.option2==null)
    {
      alert('Answer field Required');
      return
    }

    if(this.question.answer.trim()=='' || this.question.answer==null)
    {
      return
    }
    //form submit
    this._question.addQuestion(this.question).subscribe(
      (data)=>{

        Swal.fire("Success!!","Question Added Successfully",'success');
        console.log(data);
        this.question.content='';
        this.question.option1='';
        this.question.option2='';
        this.question.option3='';
        this.question.option4='';
        this.question.answer='';
      },
      (error)=>{

        Swal.fire("Error !!","Error in Adding Question","error");
        console.log(error);
      }
    )
    
    

  }


}
