package org.ukhanov.twitch.controller;

import com.github.twitch4j.TwitchClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ukhanov.twitch.config.TwitchProperties;
import org.ukhanov.twitch.service.TwitchChatService;

@RestController
@ConditionalOnBean(TwitchClient.class)
public class TwitchChatController {
    private final TwitchProperties twitchProperties;
    private final TwitchChatService chat;

    public TwitchChatController(TwitchProperties twitchProperties, TwitchChatService chat) {
        this.twitchProperties = twitchProperties;
        this.chat = chat;
    }

    @GetMapping("/send")
    public String send() {
        chat.sendMessage(twitchProperties.twitchChannel(), "Привет из Spring");
        return "ok";
    }
}
