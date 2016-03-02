package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.AffectationDAOImpl;
import fr.fscf.contacts.server.model.Affectation;
import fr.fscf.contacts.server.model.AffectationPK;

/**
 * DAO for {@link Affectation} entity.
 */
@ImplementedBy(AffectationDAOImpl.class)
public interface AffectationDAO extends DAO<Affectation, AffectationPK> {

}
