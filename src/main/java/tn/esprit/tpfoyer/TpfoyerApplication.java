package tn.esprit.tpfoyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TpfoyerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpfoyerApplication.class, args);
    }

}
