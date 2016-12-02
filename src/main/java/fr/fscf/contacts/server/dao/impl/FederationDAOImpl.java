package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.FederationDAO;
import fr.fscf.contacts.server.model.Federation;
import fr.fscf.contacts.server.model.referential.StructureType;

/**
 * DAO for {@link Federation} entity.
 */
public class FederationDAOImpl extends AbstractStructureDAO<Federation> implements FederationDAO {

    @Override
    protected String getStructureType() {
        return StructureType.FEDERATION;
    }

    // Implement specific methods here.

}
