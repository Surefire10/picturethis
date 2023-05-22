package com.picturethis.service;

import com.picturethis.entity.User;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface ImageCombolockService {


    public int[] combolockIntoArray(JSONObject combolock);
    public void saveCombolock(String currentUsername, int [] combolock);
    public String getCombolockSequenceByusername(String username);


}
