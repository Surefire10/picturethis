import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-login-done',
  templateUrl: './login-done.component.html',
  styleUrls: ['./login-done.component.css']
})
export class LoginDoneComponent {

  constructor(private router : Router){

    this.router.events
                .pipe(filter((rs): rs is NavigationEnd => rs instanceof NavigationEnd))
                .subscribe(event => {
                  if (
                    event.id === 1 &&
                    event.url === event.urlAfterRedirects
                  ) {
                    this.router.navigate(["user/log-in"]);
                    alert("Please log in again")
                  }
                })

  }

}
