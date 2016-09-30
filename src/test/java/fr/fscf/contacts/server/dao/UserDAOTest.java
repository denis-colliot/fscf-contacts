package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.User;
import org.junit.Test;

import javax.inject.Inject;

import static fr.fscf.contacts.server.config.TestDatabaseInitialization.ironman;
import static fr.fscf.contacts.server.config.TestDatabaseInitialization.superman;
import static org.assertj.core.api.Assertions.assertThat;

public class UserDAOTest extends AbstractDAOTest {

    @Inject
    private UserDAO userDAO;

    @Test
    public void should_persist_user() {
        final String email = "j.doe@email.com";

        // Check that user does not exist already.
        assertThat(userDAO.findByLogin(email)).isNull();

        final User newUser = new User();
        newUser.setName("Does");
        newUser.setFirstName("John");
        newUser.setEmail(email);
        newUser.setPassword("dummy");
        userDAO.persist(newUser, null);

        assertThat(newUser.getId()).isNotNull();

        final User persistedUser = userDAO.findByLogin(email);

        assertThat(persistedUser).isNotNull();
        assertThat(persistedUser.getId()).isEqualTo(newUser.getId());
        assertThat(persistedUser.getName()).isEqualTo(newUser.getName());
        assertThat(persistedUser.getFirstName()).isEqualTo(newUser.getFirstName());

        assertThat(userDAO.findByLogin("j.doe2@email.com")).isNull();
    }

    @Test
    public void should_find_existing_user_by_id() {
        final User user = userDAO.findById(ironman.getId());
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(ironman.getName());
        assertThat(user.getFirstName()).isEqualTo(ironman.getFirstName());
        assertThat(user.getHabilitations()).isNotEmpty();
    }

    @Test
    public void should_find_existing_user_by_login() {
        final User user = userDAO.findByLogin(ironman.getEmail());
        assertThat(user).isNotNull();
        assertThat(user.getHabilitations()).isNotEmpty();

        final User user2 = userDAO.findByLogin(superman.getEmail());
        assertThat(user2).isNotNull();
        assertThat(user2.getHabilitations()).isEmpty();
    }

}
