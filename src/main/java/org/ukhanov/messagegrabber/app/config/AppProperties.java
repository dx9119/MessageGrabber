package org.ukhanov.messagegrabber.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "app")
public record AppProperties(
        String twitchToken,
        String twitchChannel
) {}

