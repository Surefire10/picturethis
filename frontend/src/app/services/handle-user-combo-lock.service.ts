import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Response } from '../IResponse';
import { UserInformationPostService } from './user-information-post.service';
import { IDataResponse } from '../IDataResponse';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class HandleUserComboLockService {

  constructor(private http : HttpClient,
              private userInformationPostService : UserInformationPostService ) { }


  currentUserInput! : FormDataEntryValue

  comboLock = new Map<number,number>()
  

  setCurrentUserInput(username : FormDataEntryValue){

    this.currentUserInput = username;

  }

  constructComboLock(cellCoordinates: number, imageIndex:number) : Map<number,number>{

    
    this.comboLock.set(imageIndex,cellCoordinates)

    console.log(this.comboLock)
    
    return this.comboLock


  }



  submitComboLock() : Observable<Response> {
 
    console.log("submit")


    const postCombolock_url = "http://localhost:8080/set_user_combolock"
    
    const httpOptions = {
      headers: new HttpHeaders({ 'Accept': 'application/json', 'Content-Type': 'application/json' })
  };
    const combolockData = JSON.stringify( [
      // "BuffySlayer321" for testing
      {"currentUsername" : this.currentUserInput},
      {"combolock": Object.fromEntries(this.comboLock)}

     ])
    
     console.log(combolockData)
     
      //remember how subscribe actually sends the request

      return this.http.post<Response>(postCombolock_url,combolockData,httpOptions)


    

    }


    retrieveComboLock() : Observable<IDataResponse> {
      const getCombolock_url = "http://localhost:8080/get_combolock_sequence/"+this.currentUserInput


      return this.http.get<IDataResponse>(getCombolock_url)


    }
    
  

   
 

  }



