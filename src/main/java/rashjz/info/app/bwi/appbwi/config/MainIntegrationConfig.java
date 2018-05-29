package rashjz.info.app.bwi.appbwi.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.util.CallerBlocksPolicy;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.messaging.MessageHandler;
import rashjz.info.app.bwi.appbwi.listener.OrderTransactionReceiver;

import java.util.Arrays;

import static org.springframework.integration.dsl.IntegrationFlows.from;


@Configuration
@Slf4j
@EnableIntegration
public class MainIntegrationConfig {

    public static final String inputChannel = "requestChannel";
    public static final String rChannel = "replyChannel";


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
        return factory;
    }


    @Bean
    public DirectChannel requestChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow jmsOutboundGatewayFlow() {
        return from(requestChannel())
                .channel(MessageChannels.executor("httpTaskExecutorChannel", httpTaskExecutor()))
                .handle(Jms.outboundAdapter(connectionFactory())
                        .destination(OrderTransactionReceiver.HELLO_QUEUE))
                .get();
    }


    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).get();
    }

    @Bean
    public MessageHandler loggingHandler() {
        LoggingHandler logger = new LoggingHandler("INFO");
        logger.setShouldLogFullMessage(true);
        return logger;
    }
    
    @Bean
    public TaskExecutor httpTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(15);
        executor.setDaemon(true);
        executor.setThreadNamePrefix("Async-");
        executor.setRejectedExecutionHandler(new CallerBlocksPolicy(10));
        return executor;
    }
}
