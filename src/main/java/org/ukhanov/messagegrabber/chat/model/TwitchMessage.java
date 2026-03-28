package org.ukhanov.messagegrabber.chat.model;

import org.ukhanov.messagegrabber.app.model.IBrokerMessage;
import org.ukhanov.messagegrabber.app.model.MessageSource;

import java.time.ZonedDateTime;
import java.util.Objects;

public class TwitchMessage implements IBrokerMessage {
    private final MessageSource source;
    private final String name;
    private final String outerId;
    private final ZonedDateTime timeCreate;
    private final String body;

    public TwitchMessage(Builder builder) {
        this.body =builder.body;
        this.name=builder.name;
        this.outerId=builder.outerId;
        this.timeCreate=builder.timeCreate;
        this.source =builder.source;
    }

    @Override
    public MessageSource getSource() {
        return source;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOuterId() {
        return outerId;
    }

    @Override
    public ZonedDateTime getTimeCreate() {
        return timeCreate;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TwitchMessage that = (TwitchMessage) o;
        return source == that.source && Objects.equals(name, that.name) && Objects.equals(outerId, that.outerId) && Objects.equals(timeCreate, that.timeCreate) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, name, outerId, timeCreate, body);
    }

    @Override
    public String toString() {
        return  "source=" + source +
                ", name='" + name + '\'' +
                ", outerId=" + outerId +
                ", timeCreate=" + timeCreate +
                ", body='" + body + '\'';
    }

    public static class Builder {
        private MessageSource source;
        private String name;
        private String outerId;
        private ZonedDateTime timeCreate;
        private String body;

        public Builder messageSource(MessageSource messageSource){
            this.source =messageSource;
            return this;
        }

        public Builder name(String name){
            this.name=name;
            return this;
        }

        public Builder outerId(String outerId){
            this.outerId=outerId;
            return this;
        }

        public Builder timeCreate(ZonedDateTime zonedDateTime){
            this.timeCreate=zonedDateTime;
            return this;
        }

        public Builder messageBody(String messageBody){
            this.body =messageBody;
            return this;
        }

        public TwitchMessage build(){
            return new TwitchMessage(this);
        }

    }
}




