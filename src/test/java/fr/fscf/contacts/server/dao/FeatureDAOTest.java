package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.Feature;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Denis on 25/04/15.
 */
public class FeatureDAOTest extends AbstractDAOTest {

    @Inject
    private FeatureDAO featureDAO;

    @Test
    public void existToken() {
        assertThat(featureDAO.existToken("feature_1")).isTrue();
        assertThat(featureDAO.existToken("feature_2")).isTrue();
        assertThat(featureDAO.existToken("unexisting_token")).isFalse();
    }

    @Test
    public void findByToken() {
        Feature feature = featureDAO.findByToken("feature_1");
        assertThat(feature).isNotNull();
        assertThat(feature.getId()).isNotNull();

        feature = featureDAO.findByToken("feature_2");
        assertThat(feature).isNotNull();
        assertThat(feature.getId()).isNotNull();
        assertThat(feature.getToken()).isEqualTo("feature_2");

        assertThat(featureDAO.findByToken("unexisting_token")).isNull();
    }

    @Test
    public void findByTokens() {
        Map<String, Feature> features = featureDAO.findByTokens("feature_1");
        assertThat(features).isNotNull();
        assertThat(features.size()).isEqualTo(1);
        assertThat(features.get("feature_1").getToken()).isEqualTo("feature_1");

        features = featureDAO.findByTokens("feature_1", "unexisting_token");
        assertThat(features).isNotNull();
        assertThat(features.size()).isEqualTo(1);
        assertThat(features.get("feature_1").getToken()).isEqualTo("feature_1");
        assertThat(features.get("unexisting_token")).isNull();
    }

    @Test
    public void findUserFeatures() {
        final String featureToken1 = "feature_1"; // User #10 is authorized.
        final String featureToken4 = "feature_4"; // User #10 is NOT authorized.

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

        feature = featureDAO.findUserFeature(featureToken1, 10L);
        assertThat(feature).isNotNull();
        assertThat(feature.getId()).isNotNull();
        assertThat(feature.getToken()).isEqualTo(featureToken1);

        assertThat(featureDAO.findUserFeature(featureToken4, 10L)).isNull();
    }

}
