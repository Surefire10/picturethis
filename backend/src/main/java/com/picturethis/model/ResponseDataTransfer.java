package com.picturethis.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseDataTransfer {


    private HttpStatus statusCode;
    private String data;

}
