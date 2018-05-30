package rashjz.info.app.bwi.appbwi.listener;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import rashjz.info.app.bwi.appbwi.domain.OrderDetail;
import rashjz.info.app.bwi.appbwi.utils.DateUtils;

import static rashjz.info.app.bwi.appbwi.utils.DateUtils.toLocalDate;


@Slf4j
@Component
@ExtensionMethod({DateUtils.class})
public class OrderTransactionReceiver {

    public static final String HELLO_QUEUE = "hello.queue";


    @JmsListener(destination = HELLO_QUEUE, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(@Payload final OrderDetail message) {
        message.setTransactionDate(toLocalDate(message.getTransactionDate()));
        log.info("Received < < < < <" + message.toString() + ">");

    }
}
