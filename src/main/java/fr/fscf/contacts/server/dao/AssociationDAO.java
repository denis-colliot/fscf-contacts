package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.AssociationDAOImpl;
import fr.fscf.contacts.server.model.Association;

/**
 * Created by Denis on 24/04/15.
 */
@ImplementedBy(AssociationDAOImpl.class)
public interface AssociationDAO extends DAO<Association, Long> {

    // Declare specific methods here.

}
