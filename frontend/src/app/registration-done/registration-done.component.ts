import { Component } from '@angular/core';
import { NavigationEnd, Router } from "@angular/router"
import { filter } from 'rxjs/operators'


@Component({
  selector: 'app-registration-done',
  templateUrl: './registration-done.component.html',
  styleUrls: ['./registration-done.component.css']
})
export class RegistrationDoneComponent {


  constructor(private router: Router){


    this.router.events
    .pipe(filter((rs): rs is NavigationEnd => rs instanceof NavigationEnd))
    .subscribe(event => {
      if (
        event.id === 1 &&
        event.url === event.urlAfterRedirects
      ) {
        this.router.navigate(["user/sign-up"]);
        alert("Please sign up first")
      }
    })
  }


  logIn(event : any){

      this.router.navigate(["user/log-in"])

  }


}
