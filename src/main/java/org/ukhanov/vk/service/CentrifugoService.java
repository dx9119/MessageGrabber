package org.ukhanov.vk.service;

import io.github.centrifugal.centrifuge.*;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;
import org.ukhanov.vk.config.VkConfig;
import org.ukhanov.vk.config.VkProperties;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@ConditionalOnBean(VkConfig.class)
public class CentrifugoService {

    private final VkProperties vkProperties;

    public CentrifugoService(VkProperties vkProperties) throws DuplicateSubscriptionException {
        this.vkProperties = vkProperties;

        Options opts = new Options();
        opts.setTokenGetter(new ConnectionTokenGetter() {
            @Override
            public void getConnectionToken(ConnectionTokenEvent event, TokenCallback cb) {
                //GET /v1/websocket/token HTTP/1.1
                //Authorization: Bearer *
                //Content-Type: application/x-www-form-urlencoded
                //Host: apidev.live.vkvideo.ru
                cb.Done(null, vkProperties.wsToken());
            }
        });

        EventListener listener = new EventListener() {
            @Override
            public void onConnected(Client client, ConnectedEvent event) {
                System.out.printf("connected with client id %s%n", event.getClient());
            }
            @Override
            public void onConnecting(Client client, ConnectingEvent event) {
                System.out.printf("connecting: %s%n", event.getReason());
            }
            @Override
            public void onDisconnected(Client client, DisconnectedEvent event) {
                System.out.printf("disconnected %d %s%n", event.getCode(), event.getReason());
            }
            @Override
            public void onError(Client client, ErrorEvent event) {
                System.out.printf("connection error: %s%n", event.getError().toString());
            }
            @Override
            public void onMessage(Client client, MessageEvent event) {
                String data = new String(event.getData(), UTF_8);
                System.out.println("message received: " + data);
            }
            @Override
            public void onSubscribed(Client client, ServerSubscribedEvent event) {
                System.out.println("server side subscribed: " + event.getChannel() + ", recovered " + event.getRecovered());
            }
            @Override
            public void onSubscribing(Client client, ServerSubscribingEvent event) {
                System.out.println("server side subscribing: " + event.getChannel());
            }
            @Override
            public void onUnsubscribed(Client client, ServerUnsubscribedEvent event) {
                System.out.println("server side unsubscribed: " + event.getChannel());
            }
            @Override
            public void onPublication(Client client, ServerPublicationEvent event) {
                String data = new String(event.getData(), UTF_8);
                System.out.println("server side publication: " + event.getChannel() + ": " + data);
            }
            @Override
            public void onJoin(Client client, ServerJoinEvent event) {
                System.out.println("server side join: " + event.getChannel() + " from client " + event.getInfo().getClient());
            }
            @Override
            public void onLeave(Client client, ServerLeaveEvent event) {
                System.out.println("server side leave: " + event.getChannel() + " from client " + event.getInfo().getClient());
            }

        };

        SubscriptionEventListener subListener = new SubscriptionEventListener() {
            @Override
            public void onSubscribed(Subscription sub, SubscribedEvent event) {
                System.out.println("subscribed to " + sub.getChannel() + ", recovered " + event.getRecovered());
                String data="{\"input\": \"I just subscribed to channel\"}";
                sub.publish(data.getBytes(), (err, res) -> {
                    if (err != null) {
                        System.out.println("error publish: " + err.getMessage());
                        return;
                    }
                    System.out.println("successfully published");
                });
            }
            @Override
            public void onSubscribing(Subscription sub, SubscribingEvent event) {
                System.out.printf("subscribing: %s%n", event.getReason());
            }
            @Override
            public void onUnsubscribed(Subscription sub, UnsubscribedEvent event) {
                System.out.println("unsubscribed " + sub.getChannel() + ", reason: " + event.getReason());
            }
            @Override
            public void onError(Subscription sub, SubscriptionErrorEvent event) {
                System.out.println("subscription error " + sub.getChannel() + " " + event.getError().toString());
            }
            @Override
            public void onPublication(Subscription sub, PublicationEvent event) {
                String data = new String(event.getData(), UTF_8);
                System.out.println("message from " + sub.getChannel() + " " + data);
            }
            @Override
            public void onJoin(Subscription sub, JoinEvent event) {
                System.out.println("client " + event.getInfo().getClient() + " joined channel " + sub.getChannel());
            }
            @Override
            public void onLeave(Subscription sub, LeaveEvent event) {
                System.out.println("client " + event.getInfo().getClient() + " left channel " + sub.getChannel());
            }
        };

        Client client = new Client(
                vkProperties.wsUrl(),
                opts,
                listener
        );
        client.connect();
        SubscriptionOptions subOpts = new SubscriptionOptions();

        //GET /v1/websocket/subscription_token HTTP/1.1
        //Authorization: Bearer *
        //Content-Type: application/x-www-form-urlencoded
        //Host: apidev.live.vkvideo.ru
        subOpts.setToken(vkProperties.wsSubToken());
        Subscription sub = client.newSubscription(vkProperties.chatId(), subOpts, subListener);
        sub.subscribe();

    }
}

