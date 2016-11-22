package fr.fscf.contacts.server.mapper.dozer;

import fr.fscf.contacts.server.mapper.BeanMapper;
import fr.fscf.contacts.server.mapper.IsMappingHandler;
import org.dozer.Mapper;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Bean mapper implementation using Dozer API.
 *
 * @author Denis
 */
public class DozerBeanMapper implements BeanMapper {

    /**
     * Mapper Dozer.
     */
    private final Mapper dozerMapper;

    @Inject
    private DozerBeanMapper(final Mapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <D> D map(final Object bean, final Class<D> destinationClass) {

        return Optional.ofNullable(bean)
                .map(b -> dozerMapper.map(b, destinationClass))
                .orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> List<D> mapCollection(final Collection<S> collection, final Class<D> destinationClass) {

        return mapCollection(collection, destinationClass, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> List<D> mapCollection(final Collection<S> collection, final Class<D> destinationClass,
                                        final IsMappingHandler<D> handler) {

        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElse(Stream.empty())
                .map(bean -> {
                    final D mapped = map(bean, destinationClass);
                    Optional.ofNullable(handler)
                            .ifPresent(h -> h.process(Optional.ofNullable(mapped)));
                    return mapped;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

}