package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.Contact;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static fr.fscf.contacts.server.config.TestDatabaseInitialization.ironman;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactDAOTest extends AbstractDAOTest {

    @Inject
    private ContactDAO contactDAO;

    @Test
    public void findUserContactsTest() {
        List<Contact> contacts = contactDAO.findUserContacts(ironman);
        assertThat(contacts).isNotNull();
    }

}
