package com.wsy.fyxw;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FyxwCommonApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {

        //不想看见 spring的logo
        SpringApplication app = new SpringApplication(FyxwCommonApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

}