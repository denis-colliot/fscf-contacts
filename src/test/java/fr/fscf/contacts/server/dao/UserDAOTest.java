package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.User;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Denis on 25/04/15.
 */
public class UserDAOTest extends AbstractDAOTest {

    @Inject
    private UserDAO userDAO;

    @Test
    public void find() {
        final User user = userDAO.findByLogin("j.doe@email.com");

        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getName()).isEqualTo("Doe");
        assertThat(user.getFirstName()).isEqualTo("John");

        assertThat(userDAO.findByLogin("j.doe2@email.com")).isNull();
    }

    @Test
    public void test() {
        final User user = userDAO.findByLogin("tony@starkindustries.com");
        assertThat(user).isNotNull();
        assertThat(user.getStructures()).isNotEmpty();
        assertThat(user.getFeatures()).isNotEmpty();

        final User user2 = userDAO.findByLogin("clark.kent@dailyplanet.com");
        assertThat(user2).isNotNull();
        assertThat(user2.getStructures()).isEmpty();
        assertThat(user2.getFeatures()).isEmpty();
    }

}
