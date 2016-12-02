package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.ComityDAO;
import fr.fscf.contacts.server.model.Comity;
import fr.fscf.contacts.server.model.referential.StructureType;

/**
 * DAO for {@link Comity} entity.
 */
public class ComityDAOImpl extends AbstractStructureDAO<Comity> implements ComityDAO {

    @Override
    protected String getStructureType() {
        return StructureType.COMITE_DEPARTEMENTAL;
    }

    // Implement specific methods here.

}
