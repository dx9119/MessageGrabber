package org.ukhanov.twitch.service;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import org.ukhanov.messagegrabber.buffer.BufferService;
import org.ukhanov.messagegrabber.model.IMessageForBroker;
import org.ukhanov.messagegrabber.model.MessageSource;
import org.ukhanov.twitch.model.TwitchMessageForBroker;

import java.time.ZonedDateTime;

@Service
@ConditionalOnBean(TwitchClient.class)
public class TwitchMessageListener {

    public TwitchMessageListener(TwitchClient client,
                                 BufferService bufferService
    ) {;
        client.getEventManager().onEvent(ChannelMessageEvent.class, event -> {
            IMessageForBroker msg = new TwitchMessageForBroker.Builder()
                    .messageSource(MessageSource.TWITCH)
                    .name(event.getChannel().getName())
                    .outerId(event.getUser().getId())
                    .timeCreate(ZonedDateTime.now())
                    .messageBody(event.getMessage())
                    .build();

            bufferService.addMsg(msg);
        });
    }
}

