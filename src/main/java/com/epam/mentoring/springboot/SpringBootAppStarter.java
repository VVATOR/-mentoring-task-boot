package com.epam.mentoring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(value = "com.epam.mentoring.springboot.*")
//@EnableAutoConfiguration
//@EnableAdminServer
public class SpringBootAppStarter {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootAppStarter.class, args);
  }
}
