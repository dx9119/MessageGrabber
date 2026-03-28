package org.ukhanov.messagegrabber.app.model;

public interface MessagePublisher {
    void publish(IBrokerMessage message);
}

