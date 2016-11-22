package fr.fscf.contacts.server.mapper;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.mapper.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.List;

/**
 * Bean mapper interface.
 *
 * @author Denis
 */
@ImplementedBy(DozerBeanMapper.class)
public interface BeanMapper {

    /**
     * Map le {@code bean} vers le type {@code destinationClass}.
     *
     * @param <D>              Le type de la classe de destination.
     * @param bean             Le bean, peut être {@code null}.
     * @param destinationClass La classe de destination.
     * @return Une instance de la classe {@code destinationClass} correspondant au mapping du {@code bean},
     * ou {@code null}.
     */
    <D> D map(Object bean, Class<D> destinationClass);

    /**
     * Map une collection d'objets vers une liste d'un autre type.
     *
     * @param <S>              Le type des objets de la collection source.
     * @param <D>              Le type de la classe de destination.
     * @param collection       La collection source.
     * @param destinationClass La classe de destination.
     * @return Une liste contenant les beans mappés de la {@code collection} source (jamais {@code null}).
     */
    <S, D> List<D> mapCollection(Collection<S> collection, Class<D> destinationClass);

    /**
     * Map une collection d'objets vers une liste d'un autre type.<br/>
     *
     * @param <S>              Le type des objets de la collection source.
     * @param <D>              Le type de la classe de destination.
     * @param collection       La collection source.
     * @param destinationClass La classe de destination.
     * @param handler          (facultatif) handler permettant d'accéder à chaque bean durant le mapping.
     * @return Une liste contenant les beans mappés de la {@code collection} source (jamais {@code null}).
     */
    <S, D> List<D> mapCollection(Collection<S> collection, Class<D> destinationClass, IsMappingHandler<D> handler);

}