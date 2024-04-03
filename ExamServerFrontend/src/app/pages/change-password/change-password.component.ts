import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {



  constructor(private matsnackbar:MatSnackBar
    ,private loginServiceObj:LoginService,
    private router:Router,
    private _routeActivated:ActivatedRoute ){}


   


  public changedata={
    confirmpassword:'',
    password:'',
    email:''
  }

  ngOnInit()
  {
    

    this.changedata.email= this._routeActivated.snapshot.params['email'];


    
  }

  formSubmit(){
    if(this.changedata.password.trim()=='' || this.changedata.password==null)
    {
      //alert('user is required');
      this.matsnackbar.open("Password is required",'',{
        duration:3000
       // verticalPosition:'top',
        //horizontalPosition:'right'

      });
      return;
    }
    if(this.changedata.confirmpassword.trim()=='' || this.changedata.confirmpassword==null)
    {
      //alert('user is required');
      this.matsnackbar.open("confirm Password is required",'',{
        duration:3000
       // verticalPosition:'top',
        //horizontalPosition:'right'

      });
      return;
    }

    if(this.changedata.password.trim() != this.changedata.confirmpassword.trim())
    {

      this.matsnackbar.open("Please Enter Matching Password",'',{
        duration:3000
      })

    }
    console.log("change data is"+this.changedata.confirmpassword);
    console.log("change data is"+this.changedata.password);
    console.log("change data is"+this.changedata.email);

    this.loginServiceObj.changePassword(this.changedata).subscribe(
      (data)=>{

        console.log("data we get in change password"+data);
        this.router.navigate(['login']);

      },
      (error)=>{
        console.log("error we get in change password"+error);


      }
    )





  }

  login()
  {
    console.log("forgot clik ");
    this.router.navigate(['login']);
  }

}
