package rashjz.info.app.bwi.appbwi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import rashjz.info.app.bwi.appbwi.domain.OrderDetail;
import rashjz.info.app.bwi.appbwi.service.RouteAndProcessService;

import java.util.Date;

@Slf4j
@RestController
public class DataGetawayController {
    @Autowired
    RouteAndProcessService processService;


    @PostMapping(path = "data/channel")
    public OrderDetail handleData(@RequestBody OrderDetail detail) {
        log.info(detail.getOrderID());


        Message<OrderDetail> message = MessageBuilder.withPayload(detail)
                .setHeader("Message_Header1", "Message_Header1_Value")
                .setHeader("Message_Header2", "Message_Header2_Value")
                .build();

        processService.messageToAcmqFlow(message);
        return detail;
    }

    @GetMapping(path = "data/info")
    public @ResponseBody
    OrderDetail handleData() {
        return OrderDetail.builder()
                .amount(1)
                .orderID("123")
                .transactionDate(new Date())
                .transactionID("35443")
                .userID("56567")
                .build();

    }
}
