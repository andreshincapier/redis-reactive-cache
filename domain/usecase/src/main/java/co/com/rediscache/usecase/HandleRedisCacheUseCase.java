package co.com.rediscache.usecase;

import co.com.rediscache.model.mutants.Mutants;
import co.com.rediscache.model.mutants.MutantsRepository;
import co.com.rediscache.model.notification.gateways.RedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.cache.CacheMono;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

@Slf4j
@RequiredArgsConstructor
public class HandleRedisCacheUseCase {

    private final MutantsRepository mutantsRepository;
    private final RedisRepository redisRepository;

    public Flux<Mutants> findAllResults() {
        return mutantsRepository.findAll();
    }

    public Mono<String> findResultById(String mutantId) {
        return CacheMono.lookup(k -> redisRepository.get(mutantId).map(Signal::next), mutantId)
            .onCacheMissResume(mutantsRepository.findById(mutantId).map(Mutants::getId))
            .andWriteWith((k, sig) -> redisRepository.save(k, sig.get()).then());
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
