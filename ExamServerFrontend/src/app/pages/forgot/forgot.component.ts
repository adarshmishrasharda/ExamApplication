import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { error } from 'console';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css']
})
export class ForgotComponent {


  constructor(private router:Router,
    private matSnackBar:MatSnackBar,
    private loginService:LoginService){}

    emailid:any

  public forgot={
    email:'',
    otp:''
    
  }

  

  otpSent:boolean=false;

  formSubmit(){

    console.log("email"+this.forgot.email);

    if(!this.otpSent)
    {
      console.log("else part of form submit")

      if(this.forgot.email.trim()=='' || this.forgot.email==null)
      {
  
        this.matSnackBar.open("Email is required",'',{
          duration:3000
         
  
        });
  
        return;
  
  
      }
  
      this.loginService.generateOtp(this.forgot).subscribe(
        (data:any)=>{
          this.emailid=this.forgot.email;
          console.log("Success");
          console.log(data);
          console.log(this.forgot)
          this.otpSent=true;
  
        },
        (error)=>{
          console.log("error");
          console.log(error);
  
        }
  
      );

    }
    else{

      console.log("else part of form submit")

      if(this.forgot.otp.trim()=='' || this.forgot.otp==null)
      {
  
        this.matSnackBar.open("otp is required",'',{
          duration:3000
         
  
        });
  
        return;
  
  
      }

      this.loginService.verifyOtp(this.forgot).subscribe(
        (data)=>{
          console.log("Success");
          console.log(this.forgot)

          console.log(data);
          this.router.navigate(['change-password',this.emailid]);

         

        },
        (error)=>{
          console.log("error");
          console.log(error);

        }
      )

    }

  }


  login()
  {
    console.log("forgot clik ");
    this.router.navigate(['login']);
  }

}
