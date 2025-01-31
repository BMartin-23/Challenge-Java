package Teco.Challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "Teco.Challenge.Source")
@EnableJpaRepositories("Teco.Challenge.Source.Repository")
public class ChallengeJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeJavaApplication.class, args);
	}

}
