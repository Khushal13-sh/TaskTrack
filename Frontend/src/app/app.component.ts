import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ToDoManager';

  isLoggedIn:number=0; //0No  //1Yes
  userid:number=0;
}
