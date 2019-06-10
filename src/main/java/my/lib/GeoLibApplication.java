package my.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GeoLibApplication {
    @Autowired
    private TestDao testDao;

    public static void main(String[] args) {
        SpringApplication.run(GeoLibApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return testDao.findById(1).get().getName();
    }
}
