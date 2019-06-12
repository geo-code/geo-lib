package my.lib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GeoLibApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeoLibApplication.class, args);
    }
}
