package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.impl.FederationDAOImpl;
import fr.fscf.contacts.server.model.Federation;

/**
 * Created by Denis on 24/04/15.
 */
@ImplementedBy(FederationDAOImpl.class)
public interface FederationDAO extends StructureDAO<Federation> {

    // Declare specific methods here.

}
