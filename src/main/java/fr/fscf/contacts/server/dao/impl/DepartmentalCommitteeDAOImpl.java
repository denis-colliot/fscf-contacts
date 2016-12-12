package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.DepartmentalCommitteeDAO;
import fr.fscf.contacts.server.model.DepartmentalCommittee;
import fr.fscf.contacts.server.model.referential.StructureType;

/**
 * DAO for {@link DepartmentalCommittee} entity.
 */
public class DepartmentalCommitteeDAOImpl extends AbstractStructureDAO<DepartmentalCommittee> implements DepartmentalCommitteeDAO {

    @Override
    protected String getStructureType() {
        return StructureType.COMITE_DEPARTEMENTAL;
    }

    // Implement specific methods here.

}
