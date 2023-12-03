import { Component } from '@angular/core';
import {Validators, FormControl, FormGroup} from "@angular/forms"
import { UserInformationValidation } from '../validators/async-user-information-validators';
import {PasswordMatchingValidator} from "../validators/password-confirmation-validator"
import { UserInformationPostService } from '../services/user-information-post.service';
import {RetrieveImageChunksService} from "../services/retrieve-image-chunks.service"
import { Router } from '@angular/router';
import { HandleUserComboLockService } from '../services/handle-user-combo-lock.service';



@Component({


  
  selector: 'app-signup',
  templateUrl: './signup.component.html', 
  styleUrls: ['./signup.component.css'],
  providers: []
  

})

  
export class SignupComponent{


   uploadedImage : any;

  constructor(
              private passwordMatchingValidator: PasswordMatchingValidator,
              private userInformationValidation: UserInformationValidation,
              private userInformationPostService: UserInformationPostService,
              private retrieveImageChunksService: RetrieveImageChunksService,
              private handleUserComboLockService : HandleUserComboLockService,
              private router: Router
              ){}




  signupForm = new FormGroup({

    firstName : new FormControl (null,
                  {validators: [Validators.required , Validators.minLength(3)]}),

    lastName:  new FormControl (null,
                  {validators: [Validators.required , Validators.minLength(3)]}),

    email: new FormControl (null, {updateOn: "blur",
                  validators: [Validators.required, Validators.email],
                  asyncValidators: this.userInformationValidation.isEmailAvailableValidator()}),

    username: new FormControl (null,{ updateOn:"blur",
                  validators:[Validators.required, Validators.minLength(5),Validators.pattern("[A-Za-z0-9\-\_]+")],
                   asyncValidators: this.userInformationValidation.isUsernameAvailableValidator(),
                  }),
    
    password: new FormControl (null,
                   {validators: [Validators.required, Validators.minLength(8), 
                    Validators.pattern("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}"),
                    ]}),

    passwordConfirmation: new FormControl(null,
                   {validators: [Validators.required, Validators.minLength(8), 
                    Validators.pattern("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}"), 
                    this.passwordMatchingValidator.isPasswordConfirmationValid()
                    ]}),


    image:  new FormControl (null,{validators: [Validators.required,]})



    })


  onImageSelection(event: any){
   
    // image size is in bytes 
 
      this.uploadedImage = event.target.files[0];
      const allowedImageSize = 5000000 // equivalent to 5 megabytes
      const currentImageSize = this.uploadedImage.size; 
      
      if(currentImageSize > allowedImageSize){

        this.signupForm.get("image")?.setErrors({isImageTooBig: true})

      }
      

  }


  onSubmit(){


   var formData = new FormData;

      formData.append("firstName", this.signupForm.get('firstName')?.value!); //! means trust me it isnt null or won't be
      formData.append("lastName", this.signupForm.get('lastName')?.value!);
      formData.append("email", this.signupForm.get('email')?.value!);
      formData.append("username", this.signupForm.get('username')?.value!);
      formData.append("password", this.signupForm.get('password')?.value!);   
      formData.append("image", this.uploadedImage)

      console.log(formData.get("firstName"))

      this.retrieveImageChunksService.setCurrentUserInput(formData.get("username")!)
      this.handleUserComboLockService.setCurrentUserInput(formData.get("username")!)
      this.userInformationPostService.submitUserInformationOnSignUp(formData)
      
        .subscribe(response => {

          if(response.message === "User Registration Successful"){

            console.log("response: "+response.message)
            this.router.navigate(["user/set-combo-lock"]);

          }

        });

    
    
  }

  onPasswordTyped(event:any){


     console.log( "pass org " + event.target.value)
     console.log( "pass conf " + this.signupForm.get('passwordConfirmation')?.value)

    if(event.target.value !== this.signupForm.get('passwordConfirmation')?.value){
      
      this.signupForm.get('passwordConfirmation')?.setErrors({isPasswordConfirmationMismatched: true})



   }
    
  }

  

        
}



    



    
    


  
  



