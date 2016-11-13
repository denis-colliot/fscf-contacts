package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.ContactDAO;
import fr.fscf.contacts.server.dao.base.AbstractDAO;
import fr.fscf.contacts.server.dao.base.DAOUtils;
import fr.fscf.contacts.server.model.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ContactDAOImpl extends AbstractDAO<Contact, Long> implements ContactDAO {

    private static final String QUERY = DAOUtils.RECURSIVE_STRUCTURES +
            " SELECT co.* FROM t_contact_co AS co " +
            " JOIN t_affectation_af AS af on af.co_id = co.co_id " +
            " JOIN structures_tree AS st on af.st_id = st.st_id ";

    @Override
    @SuppressWarnings("unchecked")
    public List<Contact> findUserContacts(final User user) {

        final Query query = em().createNativeQuery(QUERY, Contact.class);

        query.setParameter("userId", user.getId());
        query.setParameter("featureToken", "contacts");

        return query.getResultList();
    }

}
