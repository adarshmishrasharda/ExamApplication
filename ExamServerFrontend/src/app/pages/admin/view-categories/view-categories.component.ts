import { Component } from '@angular/core';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css'],
  //imports :[MatListModule,MatDividerModule]
})
export class ViewCategoriesComponent {

  categories=[
    {
      cid:23,
      title:'programming',
      description:'this is testing category',
    },
    {
      cid:23,
      title:'programming',
      description:'this is testing category',
    },
    {
      cid:23,
      title:'programming',
      description:'this is testing category',
    }
  ];

  constructor(private _category:CategoryService){}

  ngOnInit():void{
    this._category.categories().subscribe((data:any)=>{

      //on success
      this.categories=data;
      console.log(this.categories);

    },
    (error)=>{
      //on error
      console.log(error);
      Swal.fire('Error !!','Error in loading data','error');
    }
    )
  }

}
