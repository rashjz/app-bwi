package rashjz.info.app.bwi.appbwi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class AppBwiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppBwiApplication.class, args);
	}
}
