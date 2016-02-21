package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.UserDAO;
import fr.fscf.contacts.server.dao.base.AbstractDAO;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.server.model.User_;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<User, Long> implements UserDAO {

    @Override
    public User findByLogin(final String login) {
        final CriteriaBuilder builder = getCriteriaBuilder();
        final CriteriaQuery<User> query = builder.createQuery(entityClass);
        final Root<User> root = query.from(User.class);

        query.where(builder.like(root.get(User_.login), login));
        final List<User> users = find(query);

        if (CollectionUtils.size(users) > 1) {
            throw new UnsupportedOperationException("Multiple users with login '" + login + "'");
        }
        return CollectionUtils.isNotEmpty(users) ? users.get(0) : null;
    }
}
