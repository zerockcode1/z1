package org.zerock.z1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Z1Application {

    public static void main(String[] args) {
        SpringApplication.run(Z1Application.class, args);
    }

}
