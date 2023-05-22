package com.picturethis.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
//this is an entity, done to store data;
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size( min = 1, max = 50)
    private String firstName;
    @Size( min = 1, max = 50)
    private String lastName;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    @Column(length = 65) //to make room for encrypted password;
    private String password;
    @Column(nullable = true, length = 1024)
    private String image;

    private String folderPath;




}
