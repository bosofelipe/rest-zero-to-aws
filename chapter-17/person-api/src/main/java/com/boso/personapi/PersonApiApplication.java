package com.boso.personapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class PersonApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(PersonApiApplication.class, args);

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
    String result = bCryptPasswordEncoder.encode("b0s0");
    System.out.println("My hash" + result);
  }

}
