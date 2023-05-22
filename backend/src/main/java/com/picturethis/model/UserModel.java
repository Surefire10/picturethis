package com.picturethis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
//this is a DTO, used in the view layer;
//records are used in the place of these DTOs now;
public class UserModel {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String matchingPassword;
    private MultipartFile image;





}
