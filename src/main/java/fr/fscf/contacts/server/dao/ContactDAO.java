package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.ContactDAOImpl;
import fr.fscf.contacts.server.model.Contact;
import fr.fscf.contacts.server.model.User;

import java.util.List;

/**
 * DAO for {@link Contact} entity.
 */
@ImplementedBy(ContactDAOImpl.class)
public interface ContactDAO extends DAO<Contact, Long> {

    List<Contact> findUserContacts(User user);

}
