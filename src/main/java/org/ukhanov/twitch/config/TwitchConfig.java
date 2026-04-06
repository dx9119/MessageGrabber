package org.ukhanov.twitch.config;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "spring.twitch", havingValue = "on")
public class TwitchConfig {

    private final TwitchProperties twitchProperties;

    public TwitchConfig(TwitchProperties twitchProperties) {
        this.twitchProperties = twitchProperties;
    }

    @Bean
    public TwitchClient twitchClient() {
        OAuth2Credential credential = new OAuth2Credential(
                "twitch",
                twitchProperties.twitchToken()
        );

        TwitchClient client = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(credential)
                .build();

        client.getChat().joinChannel(twitchProperties.twitchChannel());

        return client;
    }
}

