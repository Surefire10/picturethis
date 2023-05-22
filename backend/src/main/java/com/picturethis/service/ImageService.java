package com.picturethis.service;


import com.picturethis.entity.User;
import com.picturethis.model.UserModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public interface ImageService {

  void saveImageEntity(User user, UserModel userModel,MultipartFile imageFromUser);
  void saveImageToDisk(UserModel userModel,MultipartFile image);
  String originalImagePath(UserModel userModel, MultipartFile image);

  void splitImage(UserModel userModel);

  String userSpecificFolderPath(UserModel userModel);
  String userSpecificFolderPath(String username);




}
