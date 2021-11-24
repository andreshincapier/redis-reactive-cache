package co.com.rediscache.config;

import co.com.rediscache.model.mutants.MutantsRepository;
import co.com.rediscache.usecase.HandleRedisCacheUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public HandleRedisCacheUseCase handleRedisCacheUseCase(MutantsRepository mutantsRepository) {
        return new HandleRedisCacheUseCase(mutantsRepository);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }
}
