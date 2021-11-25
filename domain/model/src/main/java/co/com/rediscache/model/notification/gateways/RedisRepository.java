package co.com.rediscache.model.notification.gateways;

import reactor.core.publisher.Mono;

public interface RedisRepository {
    Mono<String> save(String key, String value);
    Mono<String> get(String key);
}

