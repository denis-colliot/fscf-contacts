package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.StructureDAO;
import fr.fscf.contacts.server.dao.base.AbstractDAO;
import fr.fscf.contacts.server.model.Structure;

/**
 * Created by Denis on 24/04/15.
 */
abstract class AbstractStructureDAO<E extends Structure> extends AbstractDAO<E, Long> implements StructureDAO<E> {

    // Implement specific methods here.

}
