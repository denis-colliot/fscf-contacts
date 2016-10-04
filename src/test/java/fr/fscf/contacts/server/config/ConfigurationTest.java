package fr.fscf.contacts.server.config;

import fr.fscf.contacts.server.inject.GuiceJUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(GuiceJUnitRunner.class)
public class ConfigurationTest {

    @Inject
    private Configuration configuration;

    @Test
    public void should_return_app_name() {
        assertThat(configuration.get("app.name")).isEqualTo("Test application");
    }

    @Test
    public void should_return_proper_boolean_value() {
        assertThat(configuration.getBoolean("boolean.value.true")).isTrue();
        assertThat(configuration.getBoolean("boolean.value.false")).isFalse();
        assertThat(configuration.getBoolean("boolean.value.null")).isFalse();
        assertThat(configuration.getBoolean("boolean.value.other")).isFalse();
        assertThat(configuration.getBoolean("_unexisting_")).isNull();
    }

    @Test
    public void should_use_default_value() {
        assertThat(configuration.getBoolean("boolean.value.true", null)).isTrue();
        assertThat(configuration.getBoolean("boolean.value.true", false)).isTrue();
        assertThat(configuration.getBoolean("boolean.value.false", null)).isFalse();
        assertThat(configuration.getBoolean("boolean.value.false", true)).isFalse();
        assertThat(configuration.getBoolean("boolean.value.other", null)).isFalse();
        assertThat(configuration.getBoolean("boolean.value.other", true)).isFalse();
        assertThat(configuration.getBoolean("boolean.value.other", false)).isFalse();
        assertThat(configuration.getBoolean("_unexisting_", false)).isFalse();
        assertThat(configuration.getBoolean("_unexisting_", true)).isTrue();
        assertThat(configuration.getBoolean("_unexisting_", null)).isNull();
    }

}
