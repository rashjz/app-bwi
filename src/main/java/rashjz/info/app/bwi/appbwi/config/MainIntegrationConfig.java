package rashjz.info.app.bwi.appbwi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import rashjz.info.app.bwi.appbwi.domain.OrderDetail;

@Configuration
@Slf4j
@EnableIntegration
@MessageEndpoint
public class MainIntegrationConfig {
    public static final String inputChannel = "inputChannel";


    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = inputChannel)
    public void getInputChannelActivator(Message<OrderDetail> detailMessage) {
        log.info("::::::::::::::: " + detailMessage.getPayload());

    }

}
