package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.impl.RegionalCommitteeDAOImpl;
import fr.fscf.contacts.server.model.RegionalCommittee;

/**
 * DAO interface for {@link RegionalCommittee} entity.
 */
@ImplementedBy(RegionalCommitteeDAOImpl.class)
public interface RegionalCommitteeDAO extends StructureDAO<RegionalCommittee> {

    // Declare specific methods here.

}
