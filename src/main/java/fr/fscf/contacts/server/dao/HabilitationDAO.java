package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.HabilitationDAOImpl;
import fr.fscf.contacts.server.model.Habilitation;
import fr.fscf.contacts.server.model.HabilitationPK;

/**
 * DAO for {@link Habilitation} entity.
 */
@ImplementedBy(HabilitationDAOImpl.class)
public interface HabilitationDAO extends DAO<Habilitation, HabilitationPK> {

}
