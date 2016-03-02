package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.ContactDAO;
import fr.fscf.contacts.server.dao.base.AbstractDAO;
import fr.fscf.contacts.server.model.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ContactDAOImpl extends AbstractDAO<Contact, Long> implements ContactDAO {

    @Override
    public List<Contact> findUserContacts(final User user) {

        final CriteriaBuilder builder = getCriteriaBuilder();
        final CriteriaQuery<Contact> query = createQuery();
        final Root<Contact> contact = query.from(Contact.class);

        query.where(builder.and(

                builder.equal(contact
                        .join(Contact_.affectations)
                        .join(Affectation_.structure)
                        .join(Structure_.users)
                        .get(User_.id), user.getId()),

                builder.equal(contact
                        .join(Contact_.affectations)
                        .join(Affectation_.structure)
                        .join(Structure_.features)
                        .get(Feature_.token), "contacts")

        ));


        return find(query);
    }

}
