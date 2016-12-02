package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.StructureDAO;
import fr.fscf.contacts.server.dao.base.AbstractDAO;
import fr.fscf.contacts.server.dao.base.DAOUtils;
import fr.fscf.contacts.server.model.Structure;
import fr.fscf.contacts.server.model.User;

import javax.persistence.Query;
import java.util.List;

/**
 * Abstract methods implementations for {@link Structure} DAOs.
 */
abstract class AbstractStructureDAO<E extends Structure> extends AbstractDAO<E, Long> implements StructureDAO<E> {

    private static final String LIST_QUERY = DAOUtils.RECURSIVE_STRUCTURES +
            " SELECT * FROM structures_tree st ";

    /**
     * Returne the entity corresponding structure type.
     *
     * @return The entity corresponding structure type.
     */
    protected abstract String getStructureType();

    @Override
    @SuppressWarnings("unchecked")
    public List<E> findUserStructures(User user) {

        final Query query = em().createNativeQuery(LIST_QUERY + " WHERE st.st_type = :structureType ", entityClass);

        query.setParameter("userId", user.getId());
        query.setParameter("featureToken", "contacts");
        query.setParameter("structureType", getStructureType());

        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Structure> findUserAllStructures(User user) {

        final Query query = em().createNativeQuery(LIST_QUERY, Structure.class);

        query.setParameter("userId", user.getId());
        query.setParameter("featureToken", "contacts");

        return query.getResultList();
    }

}
