package org.ukhanov.messagegrabber.chat.twitch.service;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;
import org.springframework.stereotype.Service;
import org.ukhanov.messagegrabber.app.model.IBrokerMessage;
import org.ukhanov.messagegrabber.app.model.MessagePublisher;
import org.ukhanov.messagegrabber.app.model.MessageSource;
import org.ukhanov.messagegrabber.chat.model.TwitchMessage;

import java.time.ZonedDateTime;

@Service
public class TwitchMessageListener {

    private final MessagePublisher publisher;

    public TwitchMessageListener(TwitchClient client, MessagePublisher publisher) {
        this.publisher = publisher;

        client.getEventManager().onEvent(ChannelMessageEvent.class, event -> {
            IBrokerMessage msg = new TwitchMessage.Builder()
                    .messageSource(MessageSource.TWITCH)
                    .name(event.getChannel().getName())
                    .outerId(event.getUser().getId())
                    .timeCreate(ZonedDateTime.now())
                    .messageBody(event.getMessage())
                    .build();

            publisher.publish(msg);
        });
    }
}

