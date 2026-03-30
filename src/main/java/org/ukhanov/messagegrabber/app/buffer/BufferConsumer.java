package org.ukhanov.messagegrabber.app.buffer;

import org.springframework.stereotype.Service;
import org.ukhanov.messagegrabber.app.model.IMessageBrokerPublisher;
import org.ukhanov.messagegrabber.app.model.IMessageForBroker;

import java.util.concurrent.*;

@Service
public class BufferConsumer {

    private final ThreadPoolExecutor pool;

    public BufferConsumer(IMessageBrokerPublisher publisher, BufferService buffer) {
        pool = new ThreadPoolExecutor(1,5,60,
                TimeUnit.SECONDS,
                new SynchronousQueue<>());

        submitNext(publisher, buffer);
    }

    private void submitNext(IMessageBrokerPublisher publisher, BufferService buffer) {
        pool.submit(() -> {
            try {
                IMessageForBroker msg = buffer.getMsg();
                publisher.publish(msg);
                submitNext(publisher, buffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
