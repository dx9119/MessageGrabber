package org.ukhanov.messagegrabber.chat.twitch.config;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.ukhanov.messagegrabber.app.config.AppProperties;

@Configuration
@ConditionalOnProperty(name = "spring.twitch", havingValue = "on")
@ComponentScan("org.ukhanov.messagegrabber.chat.twitch")
public class TwitchConfig {

    private final AppProperties appProperties;

    public TwitchConfig(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Bean
    public TwitchClient twitchClient() {
        OAuth2Credential credential = new OAuth2Credential(
                "twitch",
                appProperties.twitchToken()
        );

        TwitchClient client = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(credential)
                .build();

        client.getChat().joinChannel(appProperties.twitchChannel());

        return client;
    }
}

