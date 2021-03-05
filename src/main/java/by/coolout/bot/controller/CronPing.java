package by.coolout.bot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CronPing {

    @GetMapping(value = "/")
    public void pingBot() {
        System.out.println("ping..");
    }
}
