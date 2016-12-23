package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.AffectationDAO;
import fr.fscf.contacts.server.dao.base.AbstractDAO;
import fr.fscf.contacts.server.model.*;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AffectationDAOImpl extends AbstractDAO<Affectation, AffectationPK> implements AffectationDAO {

    @Override
    public List<Affectation> findContactAffectations(final Long contactId) {
        final CriteriaBuilder builder = getCriteriaBuilder();
        final CriteriaQuery<Affectation> query = createQuery();
        final Root<Affectation> root = query.from(entityClass);

        query.where(builder.equal(root.get(Affectation_.contact), new Contact(contactId)));

        return find(query);
    }

}
