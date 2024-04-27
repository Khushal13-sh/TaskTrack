import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(public http:HttpClient, public app:AppComponent)
  {}

  username:string='abc';
  password:string='abc';

  baseUrl='http://13.201.137.178:8080/oneToMany/'

  login(){
    let url=this.baseUrl+'login'+this.username;
    this.http.post(url,this.password).subscribe((data:any)=>{

      if(data>0){
        this.app.isLoggedIn=1;
        this.app.userid=data;
      }
      else if(data==-1){
        window.alert('exception on server');
      }
      else if(data==-2){
        window.alert('username wrong');
      }
      else if(data==-3){
        window.alert('multiple account with same username');
      }
      else if(data==-4){
        window.alert('password wrong');
      }
      else{
        window.alert('something went wrong');
      }


    });
  }
}
