package com.picturethis.repository;

import com.picturethis.entity.ImageCombolock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageCombolockRepository extends JpaRepository<ImageCombolock,Long> {

    //here we save the stuff into database ,but we have to feed it through a service


    @Query(value = "select combolock from image_combolock where username = ?1",
            nativeQuery = true
    )
    String getCombolockSequenceByusername(@Param("username") String username);


}
