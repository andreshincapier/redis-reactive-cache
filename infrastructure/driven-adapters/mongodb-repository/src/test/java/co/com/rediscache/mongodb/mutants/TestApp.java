package co.com.rediscache.mongodb.mutants;

import static org.springframework.boot.SpringApplication.run;

import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApp {

    public static void main(String[] args) {
        run(TestApp.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }
}
