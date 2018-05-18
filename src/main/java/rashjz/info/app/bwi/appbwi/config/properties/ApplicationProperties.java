package rashjz.info.app.bwi.appbwi.config.properties;
 
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.activemq")
public class ApplicationProperties implements Serializable{
    private static  String brokerUrl;
    private static  String username;
    private static  String password;
    private static  String pooled;
}
