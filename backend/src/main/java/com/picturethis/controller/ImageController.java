package com.picturethis.controller;


import com.picturethis.model.ImageURLTransfer;
import com.picturethis.service.ImageService;
import com.picturethis.service.UserService;

import org.json.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ImageController {


    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);


    // images have to be below 5 megabytes;
    //Here we're going to be using a middle-man server to bypass Chrome security
    // web-server for chrome is what I'm using, on port 8886


    @GetMapping("/load_image_url/{username}/{index}")
    public ImageURLTransfer loadImageChunkLinkByIndex(@PathVariable String username , @PathVariable Long index){

        String fullImagePathFromServer = "http://localhost:8086/images/" + username +"/" + index + ".jpg";
        LOGGER.info("full image path after hitting loadImage endpoint: " + fullImagePathFromServer +" at "+ LocalTime.now());
        return new ImageURLTransfer(HttpStatus.OK, index, fullImagePathFromServer);


    }

    @GetMapping("/load_images_url/{userInput}")
    public String loadImageChunks(@PathVariable String userInput) {

        String globalUserInput = "";
        if(userInput.contains("@")){

            globalUserInput = userService.getUsernameForEmail(userInput);

        } else{

            globalUserInput = userInput;
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put("Image Array");

        for (int i = 1; i <= 4; i++) {

            String fullImagePathFromServer = "http://127.0.0.1:8886/images/" + globalUserInput + "/" + i + ".jpg";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("imageIndex",i);
            jsonObject.put("imageURL", fullImagePathFromServer);
            jsonArray.put(i,jsonObject);
        }

        return jsonArray.toString();
    }

    @GetMapping(
            value = "/load_image/{username}/{index}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )


    public  byte[] loadImageChunksByIndex(@PathVariable String username , @PathVariable int index) {


        String fullImagePath = imageService.userSpecificFolderPath(username)+"\\"+index+".jpg";
        BufferedImage imageFromUserSpecificFolder = null;
        try {
             imageFromUserSpecificFolder = ImageIO.read(new File(fullImagePath));
        } catch (IOException e){
            System.out.println("endpoint exception caught for image read");
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            assert imageFromUserSpecificFolder != null;
            ImageIO.write(imageFromUserSpecificFolder, "jpg", baos);
        } catch (IOException e){
            System.out.println("endpoint exception caught for image write");
        }
        byte[] imageBytes = baos.toByteArray();

        LOGGER.info("full image path after hitting loadImage endpoint: " + fullImagePath +" at "+ LocalTime.now());

        return imageBytes;

    }

    @Deprecated
    @GetMapping(
            value = "/load_images",
            produces = MediaType.IMAGE_JPEG_VALUE

    )
    public @ResponseBody byte[] loadAllImageChunks() {


        int counter = 1;
        String username = userService.getCurrentUsername();
        byte[] imageBytes = null;

        while(counter <= 4) {

            String folderToLookInto = imageService.userSpecificFolderPath(username);
            String fullImagePath = folderToLookInto + "\\" + counter + ".jpg";
            BufferedImage imageFromUserSpecificFolder = null;

            try {

                imageFromUserSpecificFolder =  ImageIO.read(new File(fullImagePath));
            } catch (IOException e){

                System.out.println("endpoint exception caught for image read DEPRECATED");
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                assert imageFromUserSpecificFolder != null;
                ImageIO.write(imageFromUserSpecificFolder, "jpg", baos);
            } catch (IOException e){

                System.out.println("endpoint exception caught for image write DEPRECATED");
            }
            imageBytes = baos.toByteArray();
            counter++;
        }




        return imageBytes;

    }




}
