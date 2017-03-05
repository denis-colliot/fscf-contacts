package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.model.Structure;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.shared.dto.referential.StructureType;

import java.util.Collection;
import java.util.List;

/**
 * DAO interface far {@link Structure} DAOs.
 */
public interface StructureDAO<E extends Structure> extends DAO<E, Long> {

    /**
     * Returns the structures handled by current DAO and accessible to the given user.<br/>
     * Each {@link StructureDAO} implementation returns its own type of structures.
     *
     * @param user The authenticated user.
     * @return The structures list handled by current DAO and accessible to the given user.
     */
    List<E> findUserStructures(User user);

    /**
     * Returns <b>all</b> the structures accessible to the given user.<br/>
     * Every {@link StructureDAO} implementation returns the same data list.
     *
     * @param user The authenticated user.
     * @return The fulle structures list accessible to the given user.
     */
    List<Structure> findUserAllStructures(User user);

    /**
     * Returns <b>all</b> the structures accessible to the given user.<br/>
     * Every {@link StructureDAO} implementation returns the same data list.
     *
     * @param user  The authenticated user.
     * @param types A collection of {@link StructureType} filtering the results.
     * @return The fulle structures list accessible to the given user.
     */
    List<Structure> findUserAllStructures(User user, Collection<StructureType> types);

}
