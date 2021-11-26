package co.com.rediscache.usecase;

import co.com.rediscache.model.mutants.Mutants;
import co.com.rediscache.model.mutants.MutantsRepository;
import co.com.rediscache.model.notification.gateways.RedisRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import reactor.cache.CacheMono;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

@Log
@RequiredArgsConstructor
public class HandleRedisCacheUseCase {

    private final MutantsRepository mutantsRepository;
    private final RedisRepository redisRepository;


    public Flux<Mutants> findAllResults() {
        return mutantsRepository.findAll();
    }

    public Mono<String> findResultById(String mutantId) {
        return CacheMono.lookup(k -> redisRepository.get(mutantId).map(Signal::next), mutantId)
            .onCacheMissResume(mutantsRepository.findById(mutantId).map(this::serialize))
            .andWriteWith((k, sig) -> redisRepository.save(k, sig.get()).then());
    }


    public String serialize(Mutants obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (obj == null) {
                return null;
            }
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.severe(e.getMessage());
            return null;
        }
    }

    public Mutants deserializeTo(String obj, Class<Mutants> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (obj == null || clazz == null)
                return null;
            return objectMapper.readValue(obj, clazz);
        } catch (Exception e) {
            log.severe(e.getMessage());
            return null;
        }
    }

    //    public Mono<Mutants> findResultById(String mutantId) {
//        AtomicReference<Context> storeRef = new AtomicReference<>(Context.empty());
//        return lookup(k -> Mono.justOrEmpty(storeRef.get().<Mutants>getOrEmpty(k))
//            .map(Signal::next), mutantId)
//            .onCacheMissResume(mutantsRepository.findById(mutantId))
//            .andWriteWith((k, sig) -> Mono.fromRunnable(() ->
//                storeRef.updateAndGet(ctx -> ctx.put(k, requireNonNull(sig.get())))));
//
//    }
}
