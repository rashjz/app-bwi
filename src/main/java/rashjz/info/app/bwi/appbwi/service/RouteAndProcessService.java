package rashjz.info.app.bwi.appbwi.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import rashjz.info.app.bwi.appbwi.config.MainIntegrationConfig;

@MessagingGateway
public interface RouteAndProcessService {

    @Gateway(requestChannel = MainIntegrationConfig.inputChannel)
    void uppercase(Message<?> message);
}
