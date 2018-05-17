package rashjz.info.app.bwi.appbwi.domain;

 import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
 import lombok.*;

 import java.io.Serializable;
import java.util.Date;


@JsonDeserialize
@Data
@Builder
public class OrderDetail implements Serializable{

    private String transactionID;
    private String userID;
    private String orderID;
    private int amount;
    private Date transactionDate;

}
