import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import { DragDropModule } from "@angular/cdk/drag-drop"
import {HttpClientModule} from "@angular/common/http";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatGridListModule} from '@angular/material/grid-list';

import { AppRoutingModule,RoutingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { GetstartedComponent } from './getstarted/getstarted.component';
import { InvalidroutComponent } from './invalidrout/invalidrout.component';
import { LoginComponent } from './login/login.component';
import { ImageComboLockComponent } from './combolock-set/image-combo-lock.component';


import { UserInformationValidationService } from './validators/user-information-validation.service';
import {UserInformationPostService} from "./services/user-information-post.service";
import {RetrieveImageChunksService} from "./services/retrieve-image-chunks.service"
import {HandleUserComboLockService} from "./services/handle-user-combo-lock.service";
import { RegistrationDoneComponent } from './registration-done/registration-done.component';
import { ImageComboLockGetComponent } from './combolock-get/image-combo-lock-get.component';
import { LoginDoneComponent } from './login-done/login-done.component';





@NgModule({
  declarations: [
    AppComponent,
    GetstartedComponent,
    RoutingComponents,
    InvalidroutComponent,
    LoginComponent,
    ImageComboLockComponent,
    RegistrationDoneComponent,
    ImageComboLockGetComponent,
    LoginDoneComponent,
    
  ],
  imports: [
    
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DragDropModule,
    MatGridListModule
    
    

  ],
  providers: [
    UserInformationValidationService,
    UserInformationPostService, 
    RetrieveImageChunksService,
    HandleUserComboLockService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
