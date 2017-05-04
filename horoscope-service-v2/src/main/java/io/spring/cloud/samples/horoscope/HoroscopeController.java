package io.spring.cloud.samples.horoscope;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HoroscopeController {

    @GetMapping("/current")
    public Horoscope currentHoroscope() {
        return new Horoscope("You will win the lottery.");
    }
}
