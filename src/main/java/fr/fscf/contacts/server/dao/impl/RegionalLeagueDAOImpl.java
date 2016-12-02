package fr.fscf.contacts.server.dao.impl;

import fr.fscf.contacts.server.dao.RegionalLeagueDAO;
import fr.fscf.contacts.server.model.RegionalLeague;
import fr.fscf.contacts.server.model.referential.StructureType;

/**
 * DAO for {@link RegionalLeague} entity.
 */
public class RegionalLeagueDAOImpl extends AbstractStructureDAO<RegionalLeague> implements RegionalLeagueDAO {

    @Override
    protected String getStructureType() {
        return StructureType.LIGUE_REGIONALE;
    }

    // Implement specific methods here.

}
