package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.FeatureDAO;
import fr.fscf.contacts.server.dao.base.AbstractDAO;
import fr.fscf.contacts.server.model.Feature;
import fr.fscf.contacts.server.model.Feature_;
import fr.fscf.contacts.server.model.Habilitation_;
import fr.fscf.contacts.server.model.User_;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FeatureDAOImpl extends AbstractDAO<Feature, Long> implements FeatureDAO {

    @Override
    public boolean existToken(final String featureToken) {

        final CriteriaBuilder builder = getCriteriaBuilder();
        final CriteriaQuery<Number> query = builder.createQuery(Number.class);
        final Root<Feature> feature = query.from(entityClass);

        query.select(builder.count(query.from(entityClass)));
        query.where(builder.equal(feature.get(Feature_.token), featureToken));

        return count(query) > 1;
    }

    @Override
    public Feature findByToken(final String featureToken) {

        final CriteriaBuilder builder = getCriteriaBuilder();
        final CriteriaQuery<Feature> query = builder.createQuery(entityClass);
        final Root<Feature> feature = query.from(entityClass);

        query.where(builder.equal(feature.get(Feature_.token), featureToken));

        final List<Feature> results = find(query);
        return CollectionUtils.isNotEmpty(results) ? results.get(0) : null;
    }

    @Override
    public Map<String, Feature> findByTokens(final String... featureTokens) {

        final CriteriaBuilder builder = getCriteriaBuilder();
        final CriteriaQuery<Feature> query = builder.createQuery(entityClass);
        final Root<Feature> feature = query.from(entityClass);

        query.where(feature.get(Feature_.token).in(Arrays.asList(featureTokens)));

        return find(query).parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Feature::getToken, Function.identity()));
    }

    @Override
    public Feature findUserFeature(final String featureToken, final Long userId) {

        final CriteriaBuilder builder = getCriteriaBuilder();
        final CriteriaQuery<Feature> query = builder.createQuery(entityClass);
        final Root<Feature> feature = query.from(entityClass);

        final Predicate tokenPredicate = builder.equal(feature.get(Feature_.token), featureToken);

        if (userId != null) {
            query.where(builder.and(
                    tokenPredicate,
                    builder.equal(feature
                            .join(Feature_.habilitations)
                            .join(Habilitation_.user)
                            .get(User_.id), userId)));
        } else {
            query.where(tokenPredicate);
        }

        final List<Feature> results = find(query);
        return CollectionUtils.isNotEmpty(results) ? results.get(0) : null;
    }
}
