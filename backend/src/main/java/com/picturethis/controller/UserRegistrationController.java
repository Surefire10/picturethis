package com.picturethis.controller;

import com.picturethis.model.ResponseTransfer;
import com.picturethis.model.UserModel;
import com.picturethis.service.ImageCombolockService;
import com.picturethis.service.ImageService;
import com.picturethis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserRegistrationController<userModel> {


         String currentUsername;

        @Autowired
        private UserService userService;


        @Autowired
        private ImageService imageService;

        @Autowired
        private ImageCombolockService imageCombolockService;

        @PostMapping("/register_user")
        public ResponseTransfer userRegistrationFormHandling
                (@ModelAttribute UserModel userModel) {


                try {
                        if (userService.isUniqueUsername(userModel) && userService.isUniqueEmail(userModel)) { //if username AND email DO NOT exist
                                userService.registerNewUser(userModel, userModel.getImage());
                                imageService.saveImageToDisk(userModel,userModel.getImage());
                                imageService.splitImage(userModel);

                                currentUsername = userModel.getUsername();



                        } else{

                                throw new SQLIntegrityConstraintViolationException("Duplicate entry for username or email column");
                        }


                } catch (SQLIntegrityConstraintViolationException e) {

                        System.out.println("SQL exception caught");

                        return new ResponseTransfer(HttpStatus.NOT_ACCEPTABLE, "User Already Exists");
                }


                return new ResponseTransfer(HttpStatus.OK, "User Registration Successful");
        }


        @GetMapping("/user_registration")
        public String userRegistrationFormHandlingGet() {


                return  "This page is post only";
        }

}