package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.dao.base.DAO;
import fr.fscf.contacts.server.model.Entity;

/**
 * Created by Denis on 24/04/15.
 */
public interface EntityDAO<E extends Entity<?>> extends DAO<E, Long> {

    // Declare specific methods here.

}
