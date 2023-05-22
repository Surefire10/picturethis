package com.picturethis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageURLTransfer {


    private HttpStatus statusCode;
    private Long index;
    private String ImageUrl;




}
