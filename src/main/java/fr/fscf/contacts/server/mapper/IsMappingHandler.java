package fr.fscf.contacts.server.mapper;

import java.util.Optional;

/**
 * Handler fournissant un accès au bean mappé durant un mapping de collection.
 *
 * @author Denis
 */
public interface IsMappingHandler<S, D> {

    /**
     * Traite le bean {@code mapped}.
     *
     * @param source Le bean source (éventuellement absent).
     * @param mapped Le bean mappé (éventuellement absent).
     */
    void process(Optional<S> source, Optional<D> mapped);

}