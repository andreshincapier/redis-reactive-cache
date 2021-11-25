package co.com.rediscache.config;

import co.com.rediscache.model.mutants.MutantsRepository;
import co.com.rediscache.model.notification.gateways.RedisRepository;
import co.com.rediscache.usecase.HandleRedisCacheUseCase;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public HandleRedisCacheUseCase handleRedisCacheUseCase(MutantsRepository mutantsRepository,
        RedisRepository redisRepository) {
        return new HandleRedisCacheUseCase(mutantsRepository, redisRepository);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }
}
