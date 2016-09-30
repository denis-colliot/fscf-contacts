package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.Feature;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Map;

import static fr.fscf.contacts.server.config.TestDatabaseInitialization.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FeatureDAOTest extends AbstractDAOTest {

    @Inject
    private FeatureDAO featureDAO;

    @Test
    public void existToken() {
        assertThat(featureDAO.existToken(feature_1.getToken())).isTrue();
        assertThat(featureDAO.existToken(feature_2.getToken())).isTrue();
        assertThat(featureDAO.existToken("unexisting_token")).isFalse();
    }

    @Test
    public void findByToken() {
        Feature feature = featureDAO.findByToken(feature_1.getToken());
        assertThat(feature).isNotNull();
        assertThat(feature.getId()).isNotNull();

        feature = featureDAO.findByToken(feature_2.getToken());
        assertThat(feature).isNotNull();
        assertThat(feature.getId()).isNotNull();
        assertThat(feature.getToken()).isEqualTo(feature_2.getToken());

        assertThat(featureDAO.findByToken("unexisting_token")).isNull();
    }

    @Test
    public void findByTokens() {
        Map<String, Feature> features = featureDAO.findByTokens(feature_1.getToken());
        assertThat(features).isNotNull();
        assertThat(features.size()).isEqualTo(1);
        assertThat(features.get(feature_1.getToken()).getToken()).isEqualTo(feature_1.getToken());

        features = featureDAO.findByTokens(feature_1.getToken(), "unexisting_token");
        assertThat(features).isNotNull();
        assertThat(features.size()).isEqualTo(1);
        assertThat(features.get(feature_1.getToken()).getToken()).isEqualTo(feature_1.getToken());
        assertThat(features.get("unexisting_token")).isNull();
    }

    @Test
    public void findUserFeatures() {
        final String featureToken1 = feature_1.getToken(); // User ironman is authorized.
        final String featureToken4 = feature_4.getToken(); // User ironman is NOT authorized.

        // --
        // Missing arg.
        // --

        assertThat(featureDAO.findUserFeature(null, null)).isNull();

        Feature feature = featureDAO.findUserFeature(featureToken1, null);
        assertThat(feature).isNotNull();
        assertThat(feature.getId()).isNotNull();
        assertThat(feature.getToken()).isEqualTo(featureToken1);

        feature = featureDAO.findUserFeature(featureToken4, null);
        assertThat(feature).isNotNull();
        assertThat(feature.getId()).isNotNull();
        assertThat(feature.getToken()).isEqualTo(featureToken4);

        // --
        // With user id.
        // --

        feature = featureDAO.findUserFeature(featureToken1, ironman.getId());
        assertThat(feature).isNotNull();
        assertThat(feature.getId()).isNotNull();
        assertThat(feature.getToken()).isEqualTo(featureToken1);

        assertThat(featureDAO.findUserFeature(featureToken4, ironman.getId())).isNull();
    }

}
