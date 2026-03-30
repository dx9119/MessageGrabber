package org.ukhanov.messagegrabber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.ukhanov.messagegrabber.app.config.AppProperties;

@SpringBootApplication(scanBasePackages = {
        "org.ukhanov.messagegrabber.app"
})
@EnableConfigurationProperties(AppProperties.class)
public class MessageGrabberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageGrabberApplication.class, args);
    }

}
