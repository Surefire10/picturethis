package com.picturethis.service;

import com.picturethis.entity.User;
import com.picturethis.model.UserModel;
import com.picturethis.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.Objects;

@Service

// you can't just load stuff into the entity directly, because here we need to encode passwords ad stuff
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageCombolockService imageCombolockService;

   static String currentUsername;

   private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);



        public User registerNewUser(UserModel userModel, MultipartFile image) {


        User user = new User();

        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setUsername(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setImage(imageService.originalImagePath(userModel, image));
        user.setFolderPath(imageService.userSpecificFolderPath(userModel));
        imageService.saveImageEntity(user, userModel, image);
        userRepository.save(user);
        currentUsername = userModel.getUsername();


        return user;
    }

    @Override
    public String getUsernameForEmail(String email) {
       return userRepository.findByemail(email).getUsername();
    }


    public String getCurrentUsername(){


        LOGGER.info("inside getcurrentUsername");
        return currentUsername;

    }


    //returns true if it IS null;
    public boolean isUniqueUsername(UserModel userModel) {

        LOGGER.info("checking if username is already registered");
        String username = userModel.getUsername();
        return Objects.equals(userRepository.findByusername(username), null);


    }
    public boolean isUniqueUsername(String username) {

        LOGGER.info("checking if username is already registered (String argument)");
        return Objects.equals(userRepository.findByusername(username), null);


    }


    //returns true if it IS null;
    public boolean isUniqueEmail(UserModel userModel) {

        LOGGER.info("checking if email is already registered");
        String email = userModel.getEmail();
        return Objects.equals(userRepository.findByemail(email) ,null);


    }

    public boolean isUniqueEmail(String email) {

        LOGGER.info("checking if email is already registered (String argument)");
        return Objects.equals(userRepository.findByemail(email) ,null);


    }

    public boolean isPasswordCorrect(String username ,String rawPassword){

        System.out.println(username + " this user/email/wtvs");

        System.out.println(rawPassword + " this is raw pass");
        LOGGER.info("checking password against the one in storage");
        System.out.println(userRepository.findEncodedPasswordByusername(username));



            return passwordEncoder.matches(rawPassword,userRepository.findEncodedPasswordByusername(username));

        }



}






