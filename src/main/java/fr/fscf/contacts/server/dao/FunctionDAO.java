package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.FunctionDAOImpl;
import fr.fscf.contacts.server.model.Function;

/**
 * DAO for {@link Function} entity.
 */
@ImplementedBy(FunctionDAOImpl.class)
public interface FunctionDAO extends DAO<Function, Long> {

    // Declare specific methods here.

}
