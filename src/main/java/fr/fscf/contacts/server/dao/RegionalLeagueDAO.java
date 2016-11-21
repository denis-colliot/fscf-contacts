package fr.fscf.contacts.server.dao;

import com.google.inject.ImplementedBy;
import fr.fscf.contacts.server.dao.impl.RegionalLeagueDAOImpl;
import fr.fscf.contacts.server.model.RegionalLeague;

/**
 * Created by Denis on 24/04/15.
 */
@ImplementedBy(RegionalLeagueDAOImpl.class)
public interface RegionalLeagueDAO extends StructureDAO<RegionalLeague> {

    // Declare specific methods here.

}
