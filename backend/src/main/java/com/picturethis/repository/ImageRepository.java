package com.picturethis.repository;

import com.picturethis.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "select folder_path from user where username = ?1",
            nativeQuery = true
    )
    String findByfolderPath(@Param("username") String username);

}
