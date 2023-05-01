package net.katherine.graphqlplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "net.katherine.graphqlplayground.lec13")
public class GraphqlPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(
				GraphqlPlaygroundApplication.class, args);
	}

}
