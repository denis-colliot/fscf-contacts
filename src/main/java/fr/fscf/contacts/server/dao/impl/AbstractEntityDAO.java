package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.EntityDAO;
import fr.fscf.contacts.server.dao.base.AbstractDAO;
import fr.fscf.contacts.server.model.Entity;

/**
 * Created by Denis on 24/04/15.
 */
abstract class AbstractEntityDAO<E extends Entity> extends AbstractDAO<E, Long> implements EntityDAO<E> {

    // Implement specific methods here.

}
