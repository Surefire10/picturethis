package com.picturethis.repository;

import com.picturethis.model.UserModel;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ImageRepositoryTest {


    @Autowired
    UserRepository userRepository;




    @Test
    public void pathTest() {

        System.out.println(userRepository.findImagePathByusername("Jbobby"));

    }

}