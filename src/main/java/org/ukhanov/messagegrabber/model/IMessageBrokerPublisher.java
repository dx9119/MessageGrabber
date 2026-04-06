package org.ukhanov.messagegrabber.model;

public interface IMessageBrokerPublisher {
    void publish(IMessageForBroker message);
}

