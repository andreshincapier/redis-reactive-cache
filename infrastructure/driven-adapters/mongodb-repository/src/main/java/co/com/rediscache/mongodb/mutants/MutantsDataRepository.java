package co.com.rediscache.mongodb.mutants;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MutantsDataRepository extends ReactiveCrudRepository<MutantsData, String>,
    ReactiveQueryByExampleExecutor<MutantsData> {

}
