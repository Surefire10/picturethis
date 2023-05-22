package com.picturethis.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {


    private final String[] WHITE_LISTED_URLs = {"/"};

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

      http.csrf().disable();
      http.authorizeHttpRequests(
              (authorizationManagerRequestMatcherRegistry) -> {
                authorizationManagerRequestMatcherRegistry
                  .antMatchers(WHITE_LISTED_URLs).permitAll();

                 }
                                );

      return http.build();

  }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11); //how many rounds of hashing
    }
}
