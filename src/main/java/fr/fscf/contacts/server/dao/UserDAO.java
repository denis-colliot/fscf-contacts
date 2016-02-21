package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.UserDAOImpl;
import fr.fscf.contacts.server.model.User;

/**
 * DAO for {@link User} entity.
 */
@ImplementedBy(UserDAOImpl.class)
public interface UserDAO extends DAO<User, Long> {

    User findByLogin(String login);

}
