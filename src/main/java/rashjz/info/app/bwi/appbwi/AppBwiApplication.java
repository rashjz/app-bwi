package rashjz.info.app.bwi.appbwi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import rashjz.info.app.bwi.appbwi.domain.OrderDetail;
import rashjz.info.app.bwi.appbwi.service.RouteAndProcessService;

@SpringBootApplication
@IntegrationComponentScan
@EnableJms
public class AppBwiApplication {



	public static void main(String[] args) {
		SpringApplication.run(AppBwiApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(RouteAndProcessService routeAndProcessService) {
        return arg -> {

			Message<OrderDetail> message = MessageBuilder.withPayload(OrderDetail.builder().amount(324).build())
					.setHeader("messageHeader", "Message_Header1_Value")
					.build();

			routeAndProcessService.messageToAcmqFlow(message);

         };
    }


}
