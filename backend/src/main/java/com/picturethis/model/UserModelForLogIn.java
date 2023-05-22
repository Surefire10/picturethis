package com.picturethis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModelForLogIn {

        private String usernameOrEmail;
        private String password;

}
