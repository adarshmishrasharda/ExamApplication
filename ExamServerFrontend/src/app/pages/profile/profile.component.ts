import { Component } from '@angular/core';

import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  user:any;

  constructor(private login:LoginService){

  }


  ngOnInit():void{
    //is me ham data jo login karane ke time local storage me save kiya tha us ki nikal rahe hai
    this.user=this.login.getUser();

    // Below me ham user ke data ko server se nikal rahe hai request kar ke

//     this.login.getCurrentUser().subscribe(
//       (user:any)=>{
// this.user=user;
      
//     },
//     (error)=>{
//       alert('error');
//     }
//     );
//   }

  }

}
