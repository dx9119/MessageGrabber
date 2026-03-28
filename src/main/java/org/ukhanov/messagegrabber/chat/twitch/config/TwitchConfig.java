package org.ukhanov.messagegrabber.chat.twitch.config;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.ukhanov.messagegrabber.app.config.AppProperties;

@Configuration
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

