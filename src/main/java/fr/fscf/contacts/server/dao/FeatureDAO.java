package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.FeatureDAOImpl;
import fr.fscf.contacts.server.model.Feature;

import java.util.Map;

/**
 * DAO for {@link Feature} entity.
 */
@ImplementedBy(FeatureDAOImpl.class)
public interface FeatureDAO extends DAO<Feature, Long> {

    /**
     * Returns if the given {@code featureToken} exists.
     *
     * @param featureToken
     *         The feature token.
     * @return {@code true} if the {@code featureToken} is present in database.
     */
    boolean existToken(String featureToken);

    /**
     * Returns the given {@code featureToken} corresponding feature.
     *
     * @param featureToken
     *         The feature token.
     * @return The given {@code featureToken} corresponding feature, or {@code null}.
     */
    Feature findByToken(String featureToken);

    /**
     * Returns the given {@code featureTokens} corresponding features.
     *
     * @param featureTokens
     *         The feature token(s).
     * @return The given {@code featureTokens} corresponding features map (never {@code null}).
     */
    Map<String, Feature> findByTokens(String... featureTokens);

    /**
     * Returns the feature corresponding to given token <b>and</b> authorized to user #{@code userId}.<br/>
     * If {@code userId} is {@code null}, returns the token corresponding feature (if it exists).
     *
     * @param userId
     *         The authenticated user id, or {@code null} if anonymous.
     * @param featureToken
     *         The feature token.
     * @return The feature authorized to user #{@code userId} or {@code null}.
     */
    Feature findUserFeature(String featureToken, Long userId);

}
