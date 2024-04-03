import { LocationStrategy } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import { FormsModule } from '@angular/forms'; 
import { ReactiveFormsModule } from '@angular/forms';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-start-quiz',
  templateUrl: './start-quiz.component.html',
  styleUrls: ['./start-quiz.component.css']
})
export class StartQuizComponent {

  qid:any;
  questions:any;
  marksGot=0;
  correctAnswers=0;
  attempted=0;
  isSubmit=false;

  timer:any;


  constructor(private locationSt:LocationStrategy,
    private _route:ActivatedRoute,
    private _questionService:QuestionService){}

  ngOnInit():void{

    this.preventBackButton();
    this.qid=this._route.snapshot.params['qid'];
    //console.log(this.qid);
    this.loadQuestions();
  }

  loadQuestions()
  {
    this._questionService.getQuestiionOfQuizForTest(this.qid).subscribe(
      (data)=>{

        this.questions=data;
        this.timer=this.questions.length*2*60;

        //ham yaha pe question ke under hi ek answer check karane ke liye 
        //key value pair bana rahe hai , jis ka use kar ke ham ye check kar aaye ki user
        // ka diya hua answer hamare answer kr sath match karta hai ya nahi

        //ab hame ye field add karane ki jarurt nahi hai kyu ki ham ne field givenAnswer backend me add kar diya hai
        // this.questions.forEach((q:any) => {
        //   q['givenAnswer']='';
          
        // });

        console.log(this.questions );
        this.startTimer();


      },
      (error)=>{
        //console.log(error);
        Swal.fire("Error","error in loading questions of quiz","error");
      }
    )
  }

  preventBackButton(){
    history.pushState(null,location.href);
    this.locationSt.onPopState(()=>{
      history.pushState(null,location.href);
    })
  }

  submitQuiz()
  {
    Swal.fire({
      title: "Do you want to submit the quiz?",
      showCancelButton: true,
      confirmButtonText: "Submit",
      icon:'info'
    }).then((e)=>{
      if(e.isConfirmed)
      {
       
        this.evalQuiz();

      }
    })
  }

  evalQuiz()
  {

    //call server to evaluate result of student
    this._questionService.evalQuiz(this.questions).subscribe(
      (data:any)=>{
        console.log(data);
        this.marksGot=parseFloat(Number(data.marksGot).toFixed(2));
        this.correctAnswers=data.correctAnswers;
        this.attempted=data.attempted;
        this.isSubmit=true;
      
    },
    (error)=>{
      console.log(error);
    }

    );



    /** abhi tak ham yahi evaluate kar rahe the aur student ko us ka 
     * result show kar rahe the jo yahi se evaluate hua hai but ye secure tarika nahi hai
     *  secure way-- secure way ye hai ki ham data ko backend me le jaye aur vaha server (backend)
     * pe evaluate kare aur vaha se reult de
     */
     //calculation karo kyu ki confirmed press hua hai
     /*this.isSubmit=true;
     this.questions.forEach((q:any)=>{

       if(q.givenAnswer==q.answer)
       {
         this.correctAnswers++;
         let markSingle=this.questions[0].quiz.maxMarks/this.questions.length;
         this.marksGot+=markSingle;
       }
       if(q.givenAnswer.trim()!='')
       {
         this.attempted++;
       }
       
     });*/
     //console.log("currect answer"+this.correctAnswers);
     //console.log("mark got"+this.marksGot);
     //console.log(this.questions);
     //console.log(this.attempted);
  }

  startTimer(){
    let t=window.setInterval(()=>{

      //code
      if(this.timer<=0)
      {
        this.evalQuiz();
        clearInterval(t);
      }
      else{
        this.timer--;
      }
    },1000
    )
  }

  getFormattedTime()
  {
    let mm=Math.floor(this.timer/60);
    let ss=this.timer-mm*60;
    return `${mm} min :${ss} sec`;
  }

  printPage()
  {
    window.print();
  }

}
