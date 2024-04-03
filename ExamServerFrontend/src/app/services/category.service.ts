import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpclient:HttpClient) { }

  //load all categories
  public categories(){
    return this.httpclient.get(`${baseUrl}/category/`);
  }
  //add category

  public addCategory(category:any)
  {
    return this.httpclient.post(`${baseUrl}/category/`,category);
  }


}
