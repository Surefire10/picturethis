package com.picturethis.repository;

import com.picturethis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    @Query(value = "select image from user where username = ?1",
            nativeQuery = true
    )
    String findImagePathByusername(@Param("username") String username);

    @Query(value = "select folder_path from user where username = ?1",
            nativeQuery = true
    )
    String findUserSpecificFolderByusername(@Param("username") String username);

    @Query(value = "select password from user where username = ?1",
            nativeQuery = true
    )
    String findEncodedPasswordByusername(@Param("username") String username);

    @Query(value = "delete from user where username = ?1",
            nativeQuery = true
    )
    void deleteUserInformation(@Param("username") String username);
    String findEncodedPasswordByemail(@Param("email") String email);
    User findByusername(String username);
    User findByemail(String email);


}
