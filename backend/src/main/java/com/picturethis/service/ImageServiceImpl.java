package com.picturethis.service;

import com.picturethis.entity.Image;
import com.picturethis.entity.User;
import com.picturethis.model.UserModel;
import com.picturethis.repository.ImageRepository;
import com.picturethis.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements  ImageService{

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final String  MAIN_IMAGE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()
            + "\\GPIS\\images";
    public void saveImageEntity(User user, UserModel userModel,MultipartFile imageFromUser){


        Image image = new Image();
        image.setImagePath(originalImagePath(userModel, imageFromUser));
        image.setUser(user);
        imageRepository.save(image);

    }
    public void saveImageToDisk(UserModel userModel, MultipartFile image){


        Path directory = Paths.get(MAIN_IMAGE_PATH);
        try {
            Path pathAndName = Paths.get(MAIN_IMAGE_PATH, "\\"+image.getOriginalFilename());
            LOGGER.info("image saved to disk in this path: " + pathAndName);


            if (!Files.exists(directory)) {

                Files.createDirectories(directory);

            }
                Files.write(pathAndName, image.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public String originalImagePath(UserModel userModel, MultipartFile image){

        // we can't grab from repo if we don't even have the path assigned yet
        // String imagePath = imageRepository.findImagePathByusername(userModel.getUsername());
        return MAIN_IMAGE_PATH + "\\" +image.getOriginalFilename();
    }
    public String userSpecificFolderPath(UserModel userModel){

        LOGGER.info("current username: " + userModel.getUsername());

        return MAIN_IMAGE_PATH + "\\" + userRepository.findUserSpecificFolderByusername(userModel.getUsername());

    }


    public String userSpecificFolderPath(String username) {

        return MAIN_IMAGE_PATH + "\\" + username;

    }




    public void splitImage(UserModel userModel) {

        String username = userModel.getUsername();
        String imagePath = userRepository.findImagePathByusername(username);
        LOGGER.info("Splitting image in path " + imagePath);
        try {
            File locationOfImageFromRepo = new File(userRepository.findImagePathByusername(username));
            BufferedImage imageFromUser = ImageIO.read(locationOfImageFromRepo);

            File locationUpUntilUsernameFolder = new File(MAIN_IMAGE_PATH +"\\"+username);

            //path object to check for the existence of this directory;
            Path pathForLocationUpUntilUsernameFolder = locationUpUntilUsernameFolder.toPath();


            int row = 2;
            int column = 2;

            int totalHeight = imageFromUser.getHeight();
            int totalWidth = imageFromUser.getWidth();

            int subimageHeight = totalHeight / row;
            int subimageWidth = totalWidth / column;

            int x = 0;
            int y;
            int counter = 1;

            for (int i = 0; i < row; i++) {

                y = 0;

                for (int j = 0; j < column; j++) {

                    try {

                        if (!Files.exists(pathForLocationUpUntilUsernameFolder)){

                            Files.createDirectories(pathForLocationUpUntilUsernameFolder);
                        }

                        BufferedImage SubImage = imageFromUser.getSubimage(y, x, subimageWidth, subimageHeight);
                        //C:/entire/path/to/images/imagesnames.jpg"
                        File outputfile = new File(locationUpUntilUsernameFolder.toString() +"\\"+counter+".jpg"); //this creates the actual end images and their names
                        ImageIO.write(SubImage, "jpg", outputfile);

                        counter++;

                        y += subimageWidth;

                    } catch (IOException e) {
                        LOGGER.info("something went wrong");

                    }
                }

                x += subimageHeight;

                locationOfImageFromRepo.delete();



            }


        } catch (IOException e){

            LOGGER.info("Splitting image in path " + imagePath + " something went wrong");


        }




    }





}
