package fr.fscf.contacts.server.mapper;

import java.util.Optional;

/**
 * Handler fournissant un accès au bean mappé durant un mapping de collection.
 *
 * @author Denis
 */
public interface IsMappingHandler<D> {

    /**
     * Traite le bean {@code mapped}.
     *
     * @param mapped Le bean mappé (éventuellement absent).
     */
    void process(Optional<D> mapped);

}