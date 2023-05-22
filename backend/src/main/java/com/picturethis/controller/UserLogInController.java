package com.picturethis.controller;

import com.picturethis.model.ResponseDataTransfer;
import com.picturethis.model.ResponseTransfer;
import com.picturethis.model.UserModelForLogIn;
import com.picturethis.service.ImageCombolockService;
import com.picturethis.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserLogInController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageCombolockService imageCombolockService;

    @PostMapping(value = "/authenticate_user_password")
    //FormData tend to go into ModelAttribute
    public ResponseTransfer logInForPassword(@ModelAttribute UserModelForLogIn userModel){

        String username = "";
        if(userModel.getUsernameOrEmail().contains("@")) {
            username = userService.getUsernameForEmail(userModel.getUsernameOrEmail());
        } else{

            username = userModel.getUsernameOrEmail();
        }

        if(userService.isPasswordCorrect(username,userModel.getPassword())){
            return new ResponseTransfer(HttpStatus.OK, "Password is Correct");

        } else{

            return new ResponseTransfer(HttpStatus.NOT_ACCEPTABLE, "Password is Incorrect");

        }


    }

    @GetMapping(value = "/get_username_for_email/{email}")
    public ResponseDataTransfer getUsernameForEmail(@PathVariable String email){

        return new ResponseDataTransfer(HttpStatus.OK,userService.getUsernameForEmail(email));

    }



    @GetMapping(value = "/get_combolock_sequence/{userInput}")
    public ResponseDataTransfer getCombolockSequenceByUsername(@PathVariable String userInput){
        String globalUserInput = "";
        if(userInput.contains("@")){

            globalUserInput = userService.getUsernameForEmail(userInput);

        } else{

            globalUserInput = userInput;
        }
        System.out.println(imageCombolockService.getCombolockSequenceByusername(globalUserInput));

        return new ResponseDataTransfer(HttpStatus.OK,imageCombolockService.getCombolockSequenceByusername(globalUserInput));

    }

}

