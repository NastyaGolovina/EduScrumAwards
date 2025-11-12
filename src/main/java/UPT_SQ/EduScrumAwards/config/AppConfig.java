package UPT_SQ.EduScrumAwards.config;

import UPT_SQ.EduScrumAwards.model.Global;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public Global manager() {
        return new Global();
    }
}