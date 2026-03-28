package org.ukhanov.messagegrabber.chat.twitch.service;

import com.github.twitch4j.TwitchClient;
import org.springframework.stereotype.Service;

@Service
public class TwitchChatService {

    private final TwitchClient twitchClient;

    public TwitchChatService(TwitchClient twitchClient) {
        this.twitchClient = twitchClient;
    }

    public void sendMessage(String channel, String message) {
        twitchClient.getChat().sendMessage(channel, message);
    }




}

