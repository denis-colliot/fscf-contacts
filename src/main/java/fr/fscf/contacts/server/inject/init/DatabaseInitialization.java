package fr.fscf.contacts.server.inject.init;

import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.server.config.Configuration;
import fr.fscf.contacts.server.dao.FeatureDAO;
import fr.fscf.contacts.server.dao.FederationDAO;
import fr.fscf.contacts.server.dao.HabilitationDAO;
import fr.fscf.contacts.server.dao.UserDAO;
import fr.fscf.contacts.server.model.Feature;
import fr.fscf.contacts.server.model.Federation;
import fr.fscf.contacts.server.model.Habilitation;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.server.model.referential.GrantType;
import fr.fscf.contacts.server.security.impl.SecuredResources;
import fr.fscf.contacts.shared.GetConfigCommand;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Initializes the application database with default set of data.
 *
 * @author Denis
 * @see InitializationFilter
 */
@Singleton
final class DatabaseInitialization {

    @Inject
    private Configuration configuration;

    @Inject
    private UserDAO userDAO;

    @Inject
    private FeatureDAO featureDAO;

    @Inject
    private FederationDAO federationDAO;

    @Inject
    private HabilitationDAO habilitationDAO;

    /**
     * Triggers the data initialization.
     */
    void init() {

        final boolean dataInsertEnabled = configuration.getBoolean("insert.data");
        final boolean hasUsers = userDAO.countAll() > 0;

        if (hasUsers || BooleanUtils.isNotTrue(dataInsertEnabled)) {
            return;
        }

        // --
        // USERS.
        // --

        final User denis = new User();
        denis.setName("Colliot");
        denis.setFirstName("Denis");
        denis.setEmail("denis.colliot@gmail.com");
        denis.setPassword("$2a$10$sZ0Xr5EaDM6JWpnkis1bDuLJOop6vpaMolCvOYJhEpPcidu0tkXp6");
        denis.setActive(true);

        final User sebastien = new User();
        sebastien.setName("Bouvet");
        sebastien.setFirstName("Sébastien");
        sebastien.setEmail("bouvet.sebastien@gmail.com");
        sebastien.setPassword("$2a$10$sZ0Xr5EaDM6JWpnkis1bDuLJOop6vpaMolCvOYJhEpPcidu0tkXp6");
        sebastien.setActive(true);

        userDAO.persist(denis, null);
        userDAO.persist(sebastien, null);

        // --
        // FEATURES.
        // --

        final Feature missing = new Feature(SecuredResources.MISSING_TOKEN, GrantType.BOTH);
        final Feature contacts = new Feature(Page.CONTACTS, GrantType.AUTHENTICATED_ONLY);
        final Feature contact = new Feature(Page.CONTACT, GrantType.AUTHENTICATED_ONLY);
        final Feature associations = new Feature(Page.ASSOCIATIONS, GrantType.AUTHENTICATED_ONLY);
        final Feature association = new Feature(Page.ASSOCIATION, GrantType.AUTHENTICATED_ONLY);
        final Feature users = new Feature("users", GrantType.AUTHENTICATED_ONLY);
        final Feature user = new Feature("user", GrantType.AUTHENTICATED_ONLY);
        final Feature getConfig = new Feature(GetConfigCommand.class, GrantType.BOTH);

        featureDAO.persist(missing, null);
        featureDAO.persist(contacts, null);
        featureDAO.persist(contact, null);
        featureDAO.persist(associations, null);
        featureDAO.persist(association, null);
        featureDAO.persist(users, null);
        featureDAO.persist(user, null);
        featureDAO.persist(getConfig, null);

        // --
        // STRUCTURES.
        // --

        final Federation federation = new Federation();
        federation.setName("Fédération nationale");

        federationDAO.persist(federation, null);

        // --
        // HABILITATIONS.
        // --

        habilitationDAO.persist(new Habilitation(denis, contact, federation), null);
        habilitationDAO.persist(new Habilitation(denis, contacts, federation), null);
        habilitationDAO.persist(new Habilitation(denis, association, federation), null);
        habilitationDAO.persist(new Habilitation(denis, associations, federation), null);
        habilitationDAO.persist(new Habilitation(denis, user, federation), null);
        habilitationDAO.persist(new Habilitation(denis, users, federation), null);
        habilitationDAO.persist(new Habilitation(denis, contact, federation), null);
        habilitationDAO.persist(new Habilitation(denis, getConfig, federation), null);
        habilitationDAO.persist(new Habilitation(sebastien, contact, federation), null);
        habilitationDAO.persist(new Habilitation(sebastien, contacts, federation), null);
        habilitationDAO.persist(new Habilitation(sebastien, association, federation), null);
        habilitationDAO.persist(new Habilitation(sebastien, associations, federation), null);
        habilitationDAO.persist(new Habilitation(sebastien, user, federation), null);
        habilitationDAO.persist(new Habilitation(sebastien, users, federation), null);
        habilitationDAO.persist(new Habilitation(sebastien, getConfig, federation), null);

    }

}