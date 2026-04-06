package org.ukhanov.messagegrabber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ConfigurationPropertiesScan({
        "org.ukhanov.vk",
        "org.ukhanov.twitch"
})
@ComponentScan({
        "org.ukhanov.vk",
        "org.ukhanov.twitch",
        "org.ukhanov.messagegrabber"
})
public class MessageGrabberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageGrabberApplication.class, args);
    }
}