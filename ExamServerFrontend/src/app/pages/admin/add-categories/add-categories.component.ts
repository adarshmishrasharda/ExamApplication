import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-categories',
  templateUrl: './add-categories.component.html',
  styleUrls: ['./add-categories.component.css']
})
export class AddCategoriesComponent {

  category={
    title:'',
    description:'',
  };

  constructor(private _category:CategoryService,
    private _sanck:MatSnackBar){}

  formSubmit(){
    if(this.category.title.trim()=='' || this.category.title==null)
    {
      this._sanck.open("Title Required !!",'',{
        duration:3000
      })
      return;
    }

    //add now kyu ki sab kcuh sahi hai
    this._category.addCategory(this.category).subscribe(
      (data:any)=>
      {
        this.category.title='';
        this.category.description='';
        Swal.fire("Success !!","Category Added Successfully","success");
      },
      (error)=>{
        console.log(error);
        Swal.fire("Error !!","Server Error , Category Not Added",'error');
      }

    )
  }

}
