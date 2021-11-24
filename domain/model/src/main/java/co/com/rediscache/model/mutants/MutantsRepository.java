package co.com.rediscache.model.mutants;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MutantsRepository {

    Mono<Mutants> findById(String mutantId);
    Mono<Mutants> save(Mutants mutants);
    Mono<Void> saveAll(Flux<Mutants> tasks);
    Flux<Mutants> findAll();
}
