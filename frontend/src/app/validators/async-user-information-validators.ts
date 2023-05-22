
import { Injectable } from "@angular/core";
import { AbstractControl, AsyncValidatorFn, ValidationErrors,  } from "@angular/forms";
import { map, Observable} from "rxjs";
import { UserInformationValidationService } from "./user-information-validation.service";
import { UserInformationPostService } from "../services/user-information-post.service";


@Injectable({
    providedIn: 'root'
  })
export class UserInformationValidation{

    constructor(private userInformationValidationService:UserInformationValidationService,
                private userInformationPostService:UserInformationPostService){}

  isUsernameAvailableValidator(): AsyncValidatorFn{
  
        return(control: AbstractControl):  Observable<ValidationErrors | null> => {

               return this.userInformationValidationService.checkUsername(control).pipe(

                  map((result) => {

                        console.log(result.message)
                       return  result.message === "Username is Available" ? null : {isUsernameTaken: true } 
                       // remove quotes cause then it doesn't show up and the error has to be true to show up on validationErrors
                  })
                )

       }

    }


    isEmailAvailableValidator(): AsyncValidatorFn{
  
      return(control: AbstractControl):  Observable<ValidationErrors | null> => {

             return this.userInformationValidationService.checkEmail(control).pipe(

                map((result) => {

                      console.log(result.message)
                     return  result.message === "Email is Available" ? null : {isEmailTaken: true } 
                     // remove quotes cause then it doesn't show up and the error has to be true to show up on validationErrors
                })
              )

     }

  }


  isUserInputRegisteredValidator(): AsyncValidatorFn{

    return(control: AbstractControl):  Observable<ValidationErrors | null> => {

      var userInput : string = control.value
      var observable : Observable<ValidationErrors | null>
      if (userInput.includes("@")){
           observable =  this.userInformationValidationService.checkEmail(control).pipe(

              map((result) => {

                    
                   return  result.message === "Email is Taken" ? null : {isUserInputNotRegistered: true } 
                   // remove quotes cause then it doesn't show up and the error has to be true to show up on validationErrors
              })
            )

            return observable;

      } else { 


        observable =  this.userInformationValidationService.checkUsername(control).pipe(
          
        map((result) => {

              console.log(result.message)
             return  result.message === "Username is Taken" ? null : {isUserInputNotRegistered: true } 
             // remove quotes cause then it doesn't show up and the error has to be true to show up on validationErrors
        })
      )

      return observable;}


   }

}





}



