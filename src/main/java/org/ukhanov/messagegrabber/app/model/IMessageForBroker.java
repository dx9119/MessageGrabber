package org.ukhanov.messagegrabber.app.model;

import java.time.ZonedDateTime;

public interface IMessageForBroker {
    public MessageSource getSource();
    public String getName();
    public String getOuterId();
    public ZonedDateTime getTimeCreate();
    public String getBody();
}
