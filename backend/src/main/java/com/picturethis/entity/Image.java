package com.picturethis.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagePath;

    //all ops done to parent will be done to child
    @OneToOne(cascade = CascadeType.ALL) // this image is a child of the user, can't have an image without a user
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

}
