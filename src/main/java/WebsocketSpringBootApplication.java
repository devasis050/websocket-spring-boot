

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com")
@EnableAutoConfiguration
public class WebsocketSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketSpringBootApplication.class, args);
	}
}
