package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.ContactDAO;
import fr.fscf.contacts.server.dao.base.AbstractDAO;
import fr.fscf.contacts.server.dao.base.DAOUtils;
import fr.fscf.contacts.server.model.Contact;
import fr.fscf.contacts.server.model.User;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class ContactDAOImpl extends AbstractDAO<Contact, Long> implements ContactDAO {

    private static final String LIST_QUERY = DAOUtils.RECURSIVE_STRUCTURES +
            " SELECT co.* FROM t_contact_co AS co " +
            " JOIN t_affectation_af AS af on af.co_id = co.co_id " +
            " JOIN structures_tree AS st on af.st_id = st.st_id ";

    private static final String SINGLE_QUERY = LIST_QUERY +
            " WHERE co.co_id = :contactId ";

    @Override
    @SuppressWarnings("unchecked")
    public List<Contact> findUserContacts(final User user) {

        final Query query = em().createNativeQuery(LIST_QUERY, Contact.class);

        query.setParameter("userId", user.getId());
        query.setParameter("featureToken", "contacts");

        return query.getResultList();
    }

    @Override
    public Optional<Contact> findUserContact(final Long contactId, final User user) {

        final Query query = em().createNativeQuery(SINGLE_QUERY, Contact.class);

        query.setParameter("userId", user.getId());
        query.setParameter("featureToken", "contacts");
        query.setParameter("contactId", contactId);

        try {
            return Optional.of((Contact) query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
