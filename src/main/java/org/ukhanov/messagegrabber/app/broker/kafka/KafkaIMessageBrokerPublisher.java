package org.ukhanov.messagegrabber.app.broker.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.ukhanov.messagegrabber.app.model.IMessageForBroker;
import org.ukhanov.messagegrabber.app.model.IMessageBrokerPublisher;

@Service
@Profile("kafka")
public class KafkaIMessageBrokerPublisher implements IMessageBrokerPublisher {

    private final KafkaTemplate<String, String> kafka;
    private final String topic;

    public KafkaIMessageBrokerPublisher(KafkaTemplate<String, String> kafka,
                                        @Value("${spring.kafka.topic}") String topic) {
        this.kafka = kafka;
        this.topic = topic;
    }

    @Override
    public void publish(IMessageForBroker message) {
        kafka.send(topic, message.toString());
    }
}
