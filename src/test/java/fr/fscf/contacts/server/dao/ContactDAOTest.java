package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.Contact;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static fr.fscf.contacts.server.inject.TestDatabaseInitialization.batman;
import static fr.fscf.contacts.server.inject.TestDatabaseInitialization.frank;
import static fr.fscf.contacts.server.inject.TestDatabaseInitialization.ironman;
import static org.assertj.core.api.Assertions.assertThat;

public class ContactDAOTest extends AbstractDAOTest {

    @Inject
    private ContactDAO contactDAO;

    @Test
    public void findFederationUserContactsTest() {
        List<Contact> contacts = contactDAO.findUserContacts(ironman);

        assertThat(contacts).isNotNull();
    }

    @Test
    public void findRegionalLeaguenUserContactsTest() {
        List<Contact> contacts = contactDAO.findUserContacts(batman);

        assertThat(contacts).isNotNull();
        assertThat(contacts.size()).isEqualTo(1);
        assertThat(contacts.get(0).getName()).isEqualTo(frank.getName());
        assertThat(contacts.get(0).getFirstName()).isEqualTo(frank.getFirstName());
    }

}
