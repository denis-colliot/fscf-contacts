package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.impl.DepartmentalCommitteeDAOImpl;
import fr.fscf.contacts.server.model.DepartmentalCommittee;

/**
 * DAO interface for {@link DepartmentalCommittee} entity.
 */
@ImplementedBy(DepartmentalCommitteeDAOImpl.class)
public interface DepartmentalCommitteeDAO extends StructureDAO<DepartmentalCommittee> {

    // Declare specific methods here.

}
