package org.ukhanov.vk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "vk")
public record VkProperties(
    String wsToken,
    String wsSubToken,
    String chatId,
    String wsUrl
){}
