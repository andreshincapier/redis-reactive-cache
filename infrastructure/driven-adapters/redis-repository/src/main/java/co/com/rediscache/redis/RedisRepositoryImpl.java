package co.com.rediscache.redis;

import co.com.rediscache.model.notification.gateways.RedisRepository;
import io.lettuce.core.RedisClient;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RedisRepositoryImpl implements RedisRepository {

    private final RedisClient client = RedisClient.create("redis://password123@localhost:6379");
    private final RedisReactiveCommands<String, String> commands = client.connect().reactive();


    @Override
    public Mono<String> save(String key, String value) {
        log.info("SAVE IN REDIS {}", value);
        if (StringUtils.isAnyBlank(key, value)) {
            return Mono.just("ERROR");
        } else {
            return commands.set(key, value, SetArgs.Builder.ex(60)).map(r -> value);
        }
    }

    @Override
    public Mono<String> get(String key) {
        log.info("GET FROM REDIS {}", key);

        if (StringUtils.isBlank(key)) {
            return Mono.just("ERROR");
        } else {
            return commands.get(key);
        }
    }
}
