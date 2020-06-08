package my.lib;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Caffeine {
    @Scheduled(fixedDelay = 1000 * 60 * 25)
    public void sip() {
        new RestTemplate().getForObject("http://geo-lib.herokuapp.com/helath", String.class);
    }
}
