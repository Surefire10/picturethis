package com.picturethis.service;

import com.picturethis.entity.User;
import com.picturethis.model.UserModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {


    String getCurrentUsername();
    User registerNewUser(UserModel userModel, MultipartFile image);

    String getUsernameForEmail(String email);
    boolean isUniqueUsername(UserModel userModel);
    boolean isUniqueUsername(String username);

    boolean isUniqueEmail(String email);
    boolean isUniqueEmail(UserModel userModel);

    boolean isPasswordCorrect(String userInput ,String rawPassword);


}
