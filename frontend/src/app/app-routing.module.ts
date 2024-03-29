import {  NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { InvalidroutComponent } from './invalidrout/invalidrout.component';
import { GetstartedComponent } from './getstarted/getstarted.component';
import { LoginComponent } from './login/login.component';
import { ImageComboLockComponent } from "./combolock-set/image-combo-lock.component"
import {ImageComboLockGetComponent} from "./combolock-get/image-combo-lock-get.component"
import { RegistrationDoneComponent } from './registration-done/registration-done.component';
import {LoginDoneComponent} from "./login-done/login-done.component"


//all possible routs for your app goes here;
//path-component pairs;
const routes: Routes = [

      {path: "", component:GetstartedComponent},
      {path: "user/sign-up", component: SignupComponent},
      {path:"user/log-in", component: LoginComponent},
      {path:"user/set-combo-lock", component:ImageComboLockComponent },
      {path:"user/validate-image-combo-lock",component:ImageComboLockGetComponent},
      {path:"success", component : RegistrationDoneComponent},
      {path:"success-log-in", component: LoginDoneComponent},

      {path: "**", component:InvalidroutComponent} // should be there last:

      

];  

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const RoutingComponents = [SignupComponent,LoginComponent,InvalidroutComponent,GetstartedComponent, ImageComboLockComponent]