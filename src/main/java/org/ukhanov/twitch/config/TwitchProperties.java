package org.ukhanov.twitch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "tw")
public record TwitchProperties(
        String twitchToken,
        String twitchChannel
) {}

