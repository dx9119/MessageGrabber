package org.ukhanov.messagegrabber.chat.twitch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ukhanov.messagegrabber.app.config.AppProperties;
import org.ukhanov.messagegrabber.chat.twitch.service.TwitchChatService;

@RestController
public class TwitchChatController {
    private final AppProperties appProperties;
    private final TwitchChatService chat;

    public TwitchChatController(AppProperties appProperties, TwitchChatService chat) {
        this.appProperties = appProperties;
        this.chat = chat;
    }

    @GetMapping("/send")
    public String send() {
        chat.sendMessage(appProperties.twitchChannel(), "Привет из Spring");
        return "ok";
    }
}
