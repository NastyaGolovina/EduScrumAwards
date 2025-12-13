package UPT_SQ.EduScrumAwards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EnableJpaRepositories(basePackages = "UPT_SQ.EduScrumAwards.repository")
@EntityScan(basePackages = "UPT_SQ.EduScrumAwards.model")
public class EduScrumAwardsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduScrumAwardsApplication.class, args);
    }
}
