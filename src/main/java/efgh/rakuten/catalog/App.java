package efgh.rakuten.catalog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import efgh.rakuten.catalog.security.entities.Account;
import efgh.rakuten.catalog.security.repo.AccountRepo;

@EnableAutoConfiguration
@SpringBootApplication
public class App {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}

	@Bean
	CommandLineRunner init(final AccountRepo accountRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				accountRepository.save(new Account("super", "super"));
			}
		};

	}
}