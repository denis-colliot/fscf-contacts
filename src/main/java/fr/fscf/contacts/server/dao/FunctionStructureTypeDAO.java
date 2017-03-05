package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.FunctionStructureTypeDAOImpl;
import fr.fscf.contacts.server.model.FunctionStructureType;
import fr.fscf.contacts.server.model.FunctionStructureTypePK;

/**
 * DAO for {@link FunctionStructureType} entity.
 */
@ImplementedBy(FunctionStructureTypeDAOImpl.class)
public interface FunctionStructureTypeDAO extends DAO<FunctionStructureType, FunctionStructureTypePK> {

}
