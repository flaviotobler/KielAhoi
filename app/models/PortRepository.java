package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * This interface provides a non-blocking API for possibly blocking operations.
 */

@ImplementedBy(JPAPortRepository.class)
public interface PortRepository {

    CompletionStage<Port> add(Port port);

    CompletionStage<Stream<Port>> list();
}