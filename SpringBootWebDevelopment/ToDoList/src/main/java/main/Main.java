package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static final Logger loggerError = LogManager.getLogger("error");
    public static final Logger loggerInfo = LogManager.getLogger("info");

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
