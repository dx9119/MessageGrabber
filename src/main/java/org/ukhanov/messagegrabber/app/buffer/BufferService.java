package org.ukhanov.messagegrabber.app.buffer;

import org.springframework.stereotype.Service;
import org.ukhanov.messagegrabber.app.model.IMessageForBroker;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class BufferService {
    private final ArrayBlockingQueue<IMessageForBroker> bufferMessages = new ArrayBlockingQueue<>(500);

    public void addMsg(IMessageForBroker message){
        try {
            bufferMessages.offer(message,500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public IMessageForBroker getMsg(){
        try {
            return bufferMessages.take();

        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

    }
}
