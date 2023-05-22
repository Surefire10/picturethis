package com.picturethis.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCombolock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 1024)
    private String combolock;

    @Column(length = 1024)
    private String username;



}
