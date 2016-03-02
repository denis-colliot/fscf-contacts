package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.Contact;
import fr.fscf.contacts.server.model.User;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Denis on 25/04/15.
 */
public class ContactDAOTest extends AbstractDAOTest {

    @Inject
    private ContactDAO contactDAO;

    @Test
    public void findUserContactsTest() {
        List<Contact> contacts = contactDAO.findUserContacts(new User(10L));
        assertThat(contacts).isNotNull();
    }

}
