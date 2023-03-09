package flow.fileextension;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FileextensionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileextensionApplication.class, args);
	}

}
