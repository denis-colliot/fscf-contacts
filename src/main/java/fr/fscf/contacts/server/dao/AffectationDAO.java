package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.dao.impl.AffectationDAOImpl;
import fr.fscf.contacts.server.model.Affectation;
import fr.fscf.contacts.server.model.AffectationPK;

import java.util.List;

/**
 * DAO for {@link Affectation} entity.
 */
@ImplementedBy(AffectationDAOImpl.class)
public interface AffectationDAO extends DAO<Affectation, AffectationPK> {

    /**
     * Returns the given {@code contactId} corresponding associations.
     *
     * @return The contact associations list.
     */
    List<Affectation> findContactAffectations(Long contactId);

    /**
     * Removes all the given contact existing affectations.
     *
     * @param contactId The contact id.
     * @return The numner of deleted affectations.
     */
    int deleteContactAffectations(Long contactId);

}
