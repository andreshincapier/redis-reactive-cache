package co.com.rediscache.api;

import co.com.rediscache.usecase.HandleRedisCacheUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RedisApi {

    private final HandleRedisCacheUseCase handleRedisCacheUseCase;

    @GetMapping(path = "/redis/{mutantId}")
    public Mono<String> findAllResults(@PathVariable String mutantId) {
        return handleRedisCacheUseCase.findResultById(mutantId);
    }

}
