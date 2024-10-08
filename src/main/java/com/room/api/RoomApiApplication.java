package com.room.api;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class RoomApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomApiApplication.class, args);
        log.info("Starting Room API:");
    }

}
