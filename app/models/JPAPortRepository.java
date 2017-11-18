package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Provide JPA operations running inside of a thread pool sized to the connection pool
 */
public class JPAPortRepository implements PortRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public JPAPortRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Port> add(Port port) {
        return supplyAsync(() -> wrap(em -> insert(em, port)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Port>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Port insert(EntityManager em, Port port) {
        em.persist(port);
        return port;
    }

    private Stream<Port> list(EntityManager em) {
        List<Port> ports = em.createQuery("select p from Port p", Port.class).getResultList();
        return ports.stream();
    }
}