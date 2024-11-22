package gbolotin.recyclebuddy_spring_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "gbolotin.recyclebuddy_spring_server")
public class Application
{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
