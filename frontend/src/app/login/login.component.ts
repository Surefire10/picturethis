import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import { UserInformationValidation } from '../validators/async-user-information-validators';
import { UserInformationPostService } from '../services/user-information-post.service';
import { HandleUserComboLockService } from '../services/handle-user-combo-lock.service';
import { Router } from '@angular/router';
import { RetrieveImageChunksService } from '../services/retrieve-image-chunks.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private userInformationValidation:UserInformationValidation,
              private userInformationPostService:UserInformationPostService,
              private router:Router,
              private retrieveImageChunksService:RetrieveImageChunksService,
              private handleUserComboLockService: HandleUserComboLockService

              ){}

  logInForm = new FormGroup({

    usernameOrEmail: new FormControl(null, {updateOn: "blur"
    ,validators: [Validators.required],
    asyncValidators: this.userInformationValidation.isUserInputRegisteredValidator()}),

  password: new FormControl(null,{updateOn: "blur"
  ,validators: [Validators.required],
    asyncValidators: []}),

  
 })

    onSubmit(event:any){

      
    
      var formData  = new FormData;

        formData.append("usernameOrEmail", this.logInForm.get('usernameOrEmail')?.value!);
        formData.append("password", this.logInForm.get('password')?.value!);   

      

        this.userInformationPostService.submitUserInformationOnLogIn(formData)
        
          .subscribe(response => {
  
            if(response.message === "Password is Correct"){
  
              console.log("correct");
              this.logInForm.get('password')?.setErrors(null);
              this.retrieveImageChunksService.setCurrentUserInput(formData.get('usernameOrEmail')!)
              this.handleUserComboLockService.setCurrentUserInput(formData.get('usernameOrEmail')!)
              this.retrieveImageChunksService.getImageChunks()
              this.router.navigate(["user/validate_image_combo_lock"]);
              
            } else{

              this.logInForm.get('password')?.setErrors({isPasswordIncorrect: true})

            }

            console.log("response: "+response.message)


  
          });
  
      
    

  }


}
