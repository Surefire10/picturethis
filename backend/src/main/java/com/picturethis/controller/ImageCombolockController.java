package com.picturethis.controller;


import com.picturethis.entity.User;
import com.picturethis.model.ResponseTransfer;
import com.picturethis.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.picturethis.service.ImageCombolockService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ImageCombolockController {

    @Autowired
    private  ImageCombolockService imageCombolockService;

    @Autowired
    private UserService userService;




    @PostMapping(value = "/set_user_combolock")
    public ResponseTransfer setUserComboLock(User user, @RequestBody String combolockData){

        JSONArray jsonArray = new JSONArray(combolockData);
        String currentUsername = jsonArray.getJSONObject(0).get("currentUsername").toString();
        JSONObject combolock = (JSONObject) jsonArray.getJSONObject(1);

        System.out.println(currentUsername);
        System.out.println(combolock);

        if(Objects.equals(imageCombolockService.getCombolockSequenceByusername(currentUsername),null)){

            imageCombolockService.getCombolockSequenceByusername(currentUsername);
            int[] combolockArray = imageCombolockService.combolockIntoArray(combolock);

            imageCombolockService.saveCombolock(currentUsername,combolockArray);

            return new ResponseTransfer(HttpStatus.OK, "Combolock Set Successfully");

        } else{

            return new ResponseTransfer(HttpStatus.NOT_ACCEPTABLE, "User Already Exists");

        }





    }








}
