package org.ukhanov.messagegrabber.app.broker.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.ukhanov.messagegrabber.app.model.IBrokerMessage;
import org.ukhanov.messagegrabber.app.model.MessagePublisher;

@Service
@Profile("rabbit")
public class RabbitMessagePublisher implements MessagePublisher {

    private final RabbitTemplate rabbit;
    private final String exchange;
    private final String routingKey;

    public RabbitMessagePublisher(RabbitTemplate rabbit,
                                  @Value("${spring.rabbitmq.template.exchange}") String exchange,
                                  @Value("${spring.rabbitmq.template.routing-key}") String routingKey) {
        this.rabbit = rabbit;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Override
    public void publish(IBrokerMessage message) {
        rabbit.convertAndSend(exchange, routingKey, message.toString());
    }
}
