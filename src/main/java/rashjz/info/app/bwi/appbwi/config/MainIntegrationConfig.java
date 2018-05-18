package rashjz.info.app.bwi.appbwi.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import rashjz.info.app.bwi.appbwi.listener.OrderTransactionReceiver;

import java.util.Arrays;


@Configuration
@Slf4j
@EnableIntegration
public class MainIntegrationConfig {

    public static final String inputChannel = "requestChannel";

//    @Bean
//    @ConfigurationProperties(prefix = "spring.activemq")
//    public ApplicationProperties configProperties() {
//        return new ApplicationProperties();
//    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        factory.setTrustedPackages(Arrays.asList("rashjz.info.app"));

        return factory;
    }
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
//        factory.setConcurrency("3-10");
        return factory;
    }


    @Bean
    public DirectChannel requestChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow jmsOutboundGatewayFlow() {
       return IntegrationFlows.from(requestChannel())
               .handle(Jms.outboundGateway(connectionFactory())
//                       .replyContainer(c -> c.concurrentConsumers(3).sessionTransacted(true))
                       .requestDestination(OrderTransactionReceiver.HELLO_QUEUE)).get();
    }


    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).get();
    }

//    @ServiceActivator(inputChannel = inputChannel)
//    public void getInputChannelActivator(Message<OrderDetail> detailMessage) {
//    }

}
