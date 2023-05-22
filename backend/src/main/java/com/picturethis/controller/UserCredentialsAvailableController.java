package com.picturethis.controller;


import com.picturethis.model.ResponseTransfer;
import com.picturethis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200" )
@RestController
public class UserCredentialsAvailableController {


    @Autowired
    UserService userService;


  @GetMapping(value = "/checkusername/")
    public ResponseTransfer emptyUsernameCheckPoint(){

        return new ResponseTransfer(HttpStatus.NOT_ACCEPTABLE,"Username Cannot Be Empty");
    }

    @GetMapping(value = "/checkemail/")
    public ResponseTransfer emptyEmailCheckPoint(){

        return new ResponseTransfer(HttpStatus.NOT_ACCEPTABLE,"Email Cannot Be Empty");
    }


    @GetMapping(value = "/checkusername/{username}"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTransfer isUserNameAvailable(@PathVariable String username) {

        username = username.replaceAll("\\s", "");
        System.out.println(username);
        System.out.println(userService.isUniqueUsername(username));


        if (userService.isUniqueUsername(username)) {
                return new ResponseTransfer(HttpStatus.OK, "Username is Available");

            } else {

                return new ResponseTransfer(HttpStatus.NOT_ACCEPTABLE, "Username is Taken");
            }
        }


    @GetMapping(value = "/checkemail/{email}"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseTransfer isEmailAvailable(@PathVariable String email){

        email = email.replaceAll("\\s", "");
        if(userService.isUniqueEmail(email)){

            return new ResponseTransfer(HttpStatus.OK , "Email is Available");


        } else{

            return new ResponseTransfer(HttpStatus.NOT_ACCEPTABLE, "Email is Taken");
        }
    }






}
