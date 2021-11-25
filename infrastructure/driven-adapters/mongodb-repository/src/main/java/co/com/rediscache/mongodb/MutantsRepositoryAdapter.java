package co.com.rediscache.mongodb;

import co.com.rediscache.reactive.repository.mongo.AdapterOperations;
import co.com.rediscache.model.mutants.Mutants;
import co.com.rediscache.model.mutants.MutantsRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MutantsRepositoryAdapter extends
    AdapterOperations<Mutants, MutantsData, String, MutantsDataRepository> implements
    MutantsRepository {

    @Autowired
    public MutantsRepositoryAdapter(MutantsDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper,
            d -> mapper.mapBuilder(d, Mutants.MutantsBuilder.class).build());
    }

    @Override
    public Mono<Void> saveAll(Flux<Mutants> mutants) {
        return repository.saveAll(mutants.map(this::toData)).then();
    }

    @Override
    public Flux<Mutants> findAll() {
        return doQueryMany(repository.findAll());
    }

    @Override
    public Mono<Mutants> findById(String mutantId) {
        return doQuery(repository.findById(mutantId));
    }

}
