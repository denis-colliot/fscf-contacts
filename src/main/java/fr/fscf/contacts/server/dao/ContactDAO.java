package fr.fscf.contacts.server.dao;

import com.google.gwt.view.client.Range;
import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.ContactDAOImpl;
import fr.fscf.contacts.server.model.Contact;
import fr.fscf.contacts.server.model.User;

import java.util.List;
import java.util.Optional;

/**
 * DAO for {@link Contact} entity.
 */
@ImplementedBy(ContactDAOImpl.class)
public interface ContactDAO extends DAO<Contact, Long> {

    /**
     * Counts the contacts accessible to the given {@code user}.
     *
     * @param user The user.
     * @return The total number of contacts.
     */
    int countUserContacts(User user);

    /**
     * Returns the contacts list accessible to the given {@code user}.
     *
     * @param user  The user.
     * @param range (optional) The query range data.
     * @return The contacts list.
     */
    List<Contact> findUserContacts(User user, Range range);

    /**
     * Returns the contact accessible to the given {@code user} with id {@code contactId}.
     *
     * @param user      The user.
     * @param contactId The contact id.
     * @return The optional contact or {@code empty} if no contact found.
     */
    Optional<Contact> findUserContact(User user, Long contactId);

}
