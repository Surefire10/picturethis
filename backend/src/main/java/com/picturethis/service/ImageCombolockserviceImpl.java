package com.picturethis.service;

import com.picturethis.entity.ImageCombolock;
import com.picturethis.repository.ImageCombolockRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ImageCombolockserviceImpl implements ImageCombolockService{


    @Autowired
    ImageCombolockRepository imageCombolockRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(ImageCombolockserviceImpl.class);
    @Override
    public int[] combolockIntoArray(JSONObject combolock) {

        JSONObject combolockObject = (JSONObject) combolock.get("combolock");
        int [] combolockArray = new int[8];
        int counter = 1;

        for(int i = 0; i < combolockArray.length; i++){

            if(i == 0){

                combolockArray[i] = counter;


            } else if (i % 2 == 0 ){

                combolockArray[i] = ++counter;
            } else {

                combolockArray[i] = Integer.parseInt((String) combolockObject.get(String.valueOf(counter)));
                System.out.println(counter);

            }



        }
        System.out.println(" mine : " + Arrays.toString(combolockArray));
        return combolockArray;

    }

    @Override
    public void saveCombolock(String currentUsername, int[] combolock) {

        String stringCombolock =
                Arrays.stream(combolock).mapToObj(String::valueOf).collect(Collectors.joining(""));
        ImageCombolock imageCombolock = new ImageCombolock();

        imageCombolock.setCombolock(stringCombolock);
        imageCombolock.setUsername(currentUsername);
        LOGGER.info("combolock saved");

        imageCombolockRepository.save(imageCombolock);

        System.out.println(currentUsername + " " + combolock);




    }


    @Override
    public String getCombolockSequenceByusername(String username) {

        LOGGER.info("getting combolock sequence for specified username");
        System.out.println("combolock "+imageCombolockRepository.getCombolockSequenceByusername(username));

       return  imageCombolockRepository.getCombolockSequenceByusername(username);
    }


}
