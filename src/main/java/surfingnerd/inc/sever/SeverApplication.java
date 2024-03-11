package surfingnerd.inc.sever;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SeverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeverApplication.class, args);
    }
}