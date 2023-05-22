package com.picturethis.service;

import com.picturethis.model.UserModel;
import com.picturethis.repository.ImageRepository;
import com.picturethis.repository.UserRepository;
import org.apache.commons.io.IOUtils;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageServiceImplTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ImageService imageService;
    private final String  IMAGE_UPLOAD_PATH = System.getProperty("user.home") + "\\GPIS\\images";


    @After("")
    void tearDown() {
        if (!GraphicsEnvironment.isHeadless()) {
            try {
                Desktop.getDesktop().open(new File(userRepository.findImagePathByusername("unfinishedyara")));
            } catch (IOException e) {

                System.out.println("after exception");
            }
        }
    }


    @Test
    public void imageShouldBeSplitAndSaved() throws IOException {

        //just the test image;
        BufferedImage image = ImageIO.read(new File("C:/Users/Yara/GPIS/images/500x500.jpg"));

        int row = 2;
        int column = 2;

        int totalHeight = image.getHeight();
        int totalWidth = image.getWidth();

        int subimageHeight = totalHeight / row;
        int subimageWidth = totalWidth / column;

        int x = 0;
        int y = 0;
        int counter = 1;
        String directory = "C:/Users/Yara/GPIS/images";
        //where we output the image


        for (int i = 0; i < row; i++) {

            y = 0;

            for (int j = 0; j < column; j++) {

                try {
                    File outputImage = new File(directory +counter+".jpg"); // going to end up with directory and name
                    BufferedImage subImage = image.getSubimage(x, y, subimageWidth, subimageHeight);
                    ImageIO.write(subImage, "jpg", outputImage);
                    y += subimageWidth;
                    counter++;

                } catch (IOException e) {

                    System.out.println("something went wrong in image segmentation");

                }


            }
            x += subimageHeight;


        }


    }


    @Test
    public void imagePathThatsBeingSavedInTheRepo(){
        String imagePath = IMAGE_UPLOAD_PATH + "\\" +"imagname";
        System.out.println(imagePath);
    }



    @Test
    public void retrieveImageChunks() throws IOException {

        String username = "HeePew";
        Integer imageIndex = 1;
        String folderToLookInto = imageService.userSpecificFolderPath(username);
        InputStream input  = getClass().getResourceAsStream(folderToLookInto+"\\"+imageIndex+".jpg");
        System.out.println(folderToLookInto+"\\"+imageIndex+".jpg");






    }



}