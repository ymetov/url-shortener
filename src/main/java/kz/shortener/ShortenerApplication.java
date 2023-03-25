package kz.shortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"kz.shortener"})
public class ShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}

}
