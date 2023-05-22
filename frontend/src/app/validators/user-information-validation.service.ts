import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http"
import { AbstractControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { Response } from '../IResponse';

@Injectable({
  providedIn: 'root'
})
export class UserInformationValidationService {

  constructor(private http: HttpClient) { }


  checkUsername(control: AbstractControl): Observable<Response>{

    
    const checkUsername_url = "http://localhost:8080/checkusername/" + control.value;

      
     return this.http.get<Response>(checkUsername_url)


    }



  

  checkEmail(control: AbstractControl): Observable<Response>{


    const checkEmail_url = "http://localhost:8080/checkemail/" + control.value;
      
     return this.http.get<Response>(checkEmail_url)


    }



  

 

}
