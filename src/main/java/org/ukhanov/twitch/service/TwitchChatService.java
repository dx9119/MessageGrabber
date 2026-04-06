package org.ukhanov.twitch.service;

import com.github.twitch4j.TwitchClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnBean(TwitchClient.class)
public class TwitchChatService {

    private final TwitchClient twitchClient;

    public TwitchChatService(TwitchClient twitchClient) {
        this.twitchClient = twitchClient;
    }

    public void sendMessage(String channel, String message) {
        twitchClient.getChat().sendMessage(channel, message);
    }
}