import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Response } from '../IResponse';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserInformationPostService {

  
  constructor(private http : HttpClient) { }


  submitUserInformationOnSignUp(formData : any){

    const postUserInformation_url = "http://localhost:8080/register_user"

    return this.http.post<Response>(postUserInformation_url, formData);
    

  }

  submitUserInformationOnLogIn(formData : any){

    console.log(formData.get("usernameOrEmail"))

    const postUserInformation_url = "http://localhost:8080/authenticate_user_password"

    return this.http.post<Response>(postUserInformation_url, formData);
    

  }


}
