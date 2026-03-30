package org.ukhanov.messagegrabber.chat.twitch.service;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import org.springframework.stereotype.Service;
import org.ukhanov.messagegrabber.app.buffer.BufferService;
import org.ukhanov.messagegrabber.app.model.IMessageForBroker;
import org.ukhanov.messagegrabber.app.model.MessageSource;
import org.ukhanov.messagegrabber.chat.twitch.model.TwitchMessageForBroker;

import java.time.ZonedDateTime;

@Service
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

