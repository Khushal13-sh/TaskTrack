import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  tasks:any;

  baseUrl='http://13.201.137.178:8080/oneToMany/';

  constructor(public http:HttpClient, public app:AppComponent){
    let url=this.baseUrl+'readAllTasks'+this.app.userid;
    this.http.get(url).subscribe((data:any)=>{
      console.log(data);
      this.tasks=data;
    });
  }

  details:string='';
  add(){
    let url=this.baseUrl+'add'+this.app.userid;
    this.http.post(url,this.details).subscribe((data:any)=>{
      if(data==null){
        alert('Exception on server');
      }
      else{
        this.tasks.push(data);
        this.details='';
      }
    });
  }

  logout(){
    this.app.isLoggedIn=0;
  }

  delete(task:any){
    let url=this.baseUrl+'delete'+this.app.userid+'and'+task.id;
    this.http.get(url).subscribe((data:any)=>{
      if(data==1){
        let index=this.tasks.indexOf(task);
        if(index>=0){
          this.tasks.splice(index,1)
        }
      }
      else{
        alert('Exception on server')
      }
    });
  }

}
