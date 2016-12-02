package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.AssociationDAO;
import fr.fscf.contacts.server.model.Association;
import fr.fscf.contacts.server.model.referential.StructureType;

/**
 * Created by Denis on 24/04/15.
 */
public class AssociationDAOImpl extends AbstractStructureDAO<Association> implements AssociationDAO {

    @Override
    protected String getStructureType() {
        return StructureType.ASSOCIATION;
    }

    // Implement specific methods here.

}
