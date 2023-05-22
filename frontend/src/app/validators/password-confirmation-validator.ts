import { Injectable } from "@angular/core"
import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms"



@Injectable({
    providedIn: 'root'
  })
export class PasswordMatchingValidator{

    constructor() {}

  isPasswordConfirmationValid(): ValidatorFn{
  
        return(control: AbstractControl):  ValidationErrors | null => {

            var password = control.parent?.get("password");
            var passwordConfirmation = control.parent?.get("passwordConfirmation");
            
            
            if(password?.value !== passwordConfirmation?.value) {
                return {isPasswordConfirmationMismatched: true }


            } else{

                return null;


            }


       }

    }

}
