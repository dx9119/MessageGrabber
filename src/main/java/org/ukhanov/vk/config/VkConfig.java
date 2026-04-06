package org.ukhanov.vk.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "spring.vk", havingValue = "on")
public class VkConfig {

private final VkProperties vkProperties;

    public VkConfig(VkProperties vkProperties) {
        this.vkProperties = vkProperties;
    }
}
