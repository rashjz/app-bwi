package rashjz.info.app.bwi.appbwi.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import rashjz.info.app.bwi.appbwi.domain.OrderDetail;


@Slf4j
@Component
public class OrderTransactionReceiver {

    public static final String HELLO_QUEUE = "hello.queue";


    @JmsListener(destination = HELLO_QUEUE, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(@Payload final OrderDetail message) {
        log.info("Received < < < < <" + message.toString() + "> > > > >");

    }
}
