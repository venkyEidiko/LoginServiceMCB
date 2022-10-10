package customer.mbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringMcbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMcbDemoApplication.class, args);
	}

}
