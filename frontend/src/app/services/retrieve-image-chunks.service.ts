import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IImage } from '../IImage';
import {DomSanitizer} from '@angular/platform-browser';

import { UserInformationPostService } from './user-information-post.service';




@Injectable({
  providedIn: 'root'
})
export class RetrieveImageChunksService {

  constructor(private http: HttpClient,
   ) {}

   currentUserInput! : FormDataEntryValue

  


  

  setCurrentUserInput(username : FormDataEntryValue){

    this.currentUserInput = username;

  }

 

  getImageChunks() : Observable<IImage[]>{

    console.log(this.currentUserInput)
   const getImageChunks_url = "http://localhost:8080/load_images_url/"+this.currentUserInput+"/"

    return this.http.get<IImage[]>(getImageChunks_url)

     
  }

}
