package rashjz.info.app.bwi.appbwi.config.properties;
 
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class ApplicationProperties implements Serializable{
    private static  String brokerUrl;
    private static  String username;
    private static  String password;
    private static  String pooled;
}
