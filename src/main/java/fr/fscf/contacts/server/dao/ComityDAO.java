package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.impl.ComityDAOImpl;
import fr.fscf.contacts.server.model.Comity;

/**
 * Created by Denis on 24/04/15.
 */
@ImplementedBy(ComityDAOImpl.class)
public interface ComityDAO extends StructureDAO<Comity> {

    // Declare specific methods here.

}
