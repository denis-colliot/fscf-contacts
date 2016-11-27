package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.AuthenticationDAOImpl;
import fr.fscf.contacts.server.model.Authentication;

/**
 * Created by Denis on 24/04/15.
 */
@ImplementedBy(AuthenticationDAOImpl.class)
public interface AuthenticationDAO extends DAO<Authentication, String> {

    // Declare specific methods here.

}
