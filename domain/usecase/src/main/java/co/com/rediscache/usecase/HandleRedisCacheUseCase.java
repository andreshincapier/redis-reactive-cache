package co.com.rediscache.usecase;

import static java.util.Objects.requireNonNull;
import static reactor.cache.CacheMono.lookup;

import co.com.rediscache.model.mutants.Mutants;
import co.com.rediscache.model.mutants.MutantsRepository;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;
import reactor.util.context.Context;

@Slf4j
@RequiredArgsConstructor
public class HandleRedisCacheUseCase {

    private final MutantsRepository mutantsRepository;

    public Flux<Mutants> findAllResults() {
        return mutantsRepository.findAll();
    }

    public Mono<Mutants> findResultById(String mutantId) {
        AtomicReference<Context> storeRef = new AtomicReference<>(Context.empty());
        return lookup(k -> Mono.justOrEmpty(storeRef.get().<Mutants>getOrEmpty(k))
            .map(Signal::next), mutantId)
            .onCacheMissResume(mutantsRepository.findById(mutantId))
            .andWriteWith((k, sig) -> Mono.fromRunnable(() ->
                storeRef.updateAndGet(ctx -> ctx.put(k, requireNonNull(sig.get())))));

    }
}
