package org.ukhanov.messagegrabber.app.model;

public interface IMessageBrokerPublisher {
    void publish(IMessageForBroker message);
}

