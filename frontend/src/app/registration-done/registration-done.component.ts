import { Component } from '@angular/core';
import { Router } from "@angular/router"

@Component({
  selector: 'app-registration-done',
  templateUrl: './registration-done.component.html',
  styleUrls: ['./registration-done.component.css']
})
export class RegistrationDoneComponent {


  constructor(private router: Router){}


  logIn(event : any){

      this.router.navigate(["user/login"])

  }


}
