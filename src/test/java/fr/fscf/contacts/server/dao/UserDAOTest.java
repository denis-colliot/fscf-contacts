package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.User;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Denis on 25/04/15.
 */
public class UserDAOTest extends AbstractDAOTest {

    @Inject
    private UserDAO userDAO;

    @Before
    public void before() {
        final User user = new User();
        user.setName("Doe");
        user.setFirstName("John");
        user.setLogin("john.doe");
        user.setPassword("p4ssw0rd");
        user.setActive(true);
        user.setEmail("j.doe@email.com");

        userDAO.persist(user, null);
    }

    @Test
    public void find() {
        final User user = userDAO.findByLogin("john.doe");

        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getName()).isEqualTo("Doe");
        assertThat(user.getFirstName()).isEqualTo("John");

        assertThat(userDAO.findByLogin("john.doe2")).isNull();
    }

}
