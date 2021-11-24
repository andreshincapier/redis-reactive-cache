package co.com.rediscache.mongodb.mutants;

import static org.assertj.core.api.Assertions.assertThat;
import static reactor.core.publisher.Flux.just;

import co.com.rediscache.model.mutants.Mutants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantsRepositoryAdapterTest {

    @Autowired
    private MutantsRepositoryAdapter adapter;

    private final Mutants task1 = Mutants.builder().id("1").type("Task 1")
        .dnaChain("Task Desc 1").build();
    private final Mutants task2 = Mutants.builder().id("2").type("Task 2")
        .dnaChain("Task Desc 2").build();
    private final Mutants task3 = Mutants.builder().id("3").type("Task 3")
        .dnaChain("Task Desc 3").build();
    private final Mutants task4 = Mutants.builder().id("4").type("Task 4")
        .dnaChain("Task Desc 4").build();
    private final Mutants task56 = Mutants.builder().id("5").type("Task 56")
        .dnaChain("Task Desc 56").build();

    @Before
    public void saveInitialData() {
        final Mono<Void> result = adapter.saveAll(just(task1, task2, task3, task4));
        StepVerifier.create(result).verifyComplete();
    }

    @Test
    public void shouldFindAll() {
        StepVerifier.create(adapter.findAll().collectList())
            .assertNext(tasks -> assertThat(tasks).contains(task1, task2, task3, task4))
            .verifyComplete();
    }
}