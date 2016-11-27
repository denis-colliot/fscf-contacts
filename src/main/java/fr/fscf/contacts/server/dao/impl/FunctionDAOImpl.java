package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.FunctionDAO;
import fr.fscf.contacts.server.dao.base.AbstractDAO;
import fr.fscf.contacts.server.model.Function;
import fr.fscf.contacts.server.model.Function_;
import fr.fscf.contacts.server.model.User;
import org.hibernate.query.criteria.internal.OrderImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.List;

public class FunctionDAOImpl extends AbstractDAO<Function, Long> implements FunctionDAO {

    @Override
    public List<Function> findAll() {
        final Root<Function> root = createQuery().from(entityClass);
        return find(createQuery().orderBy(getCriteriaBuilder().asc(root.get(Function_.name))));
    }

}
