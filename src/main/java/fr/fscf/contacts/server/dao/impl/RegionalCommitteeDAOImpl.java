package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.RegionalCommitteeDAO;
import fr.fscf.contacts.server.model.RegionalCommittee;
import fr.fscf.contacts.shared.dto.referential.StructureType;

/**
 * DAO for {@link RegionalCommittee} entity.
 */
public class RegionalCommitteeDAOImpl extends AbstractStructureDAO<RegionalCommittee> implements RegionalCommitteeDAO {

    @Override
    protected StructureType getStructureType() {
        return StructureType.COMITE_REGIONAL;
    }

    // Implement specific methods here.

}
