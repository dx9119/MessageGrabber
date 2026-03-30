package org.ukhanov.messagegrabber.app.broker.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.ukhanov.messagegrabber.app.model.IMessageForBroker;
import org.ukhanov.messagegrabber.app.model.IMessageBrokerPublisher;

@Service
@Profile("rabbit")
public class RabbitIMessageBrokerPublisher implements IMessageBrokerPublisher {

    private final RabbitTemplate rabbit;
    private final String exchange;
    private final String routingKey;

    public RabbitIMessageBrokerPublisher(RabbitTemplate rabbit,
                                         @Value("${spring.rabbitmq.template.exchange}") String exchange,
                                         @Value("${spring.rabbitmq.template.routing-key}") String routingKey) {
        this.rabbit = rabbit;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Override
    public void publish(IMessageForBroker message) {
        rabbit.convertAndSend(exchange, routingKey, message.toString());
    }
}
