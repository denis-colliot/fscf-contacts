package fr.fscf.contacts.server.config.init;

import fr.fscf.contacts.server.dao.FeatureDAO;
import fr.fscf.contacts.server.dao.FederationDAO;
import fr.fscf.contacts.server.dao.UserDAO;
import fr.fscf.contacts.server.model.Feature;
import fr.fscf.contacts.server.model.Federation;
import fr.fscf.contacts.server.model.User;
import fr.fscf.contacts.server.model.referential.GrantType;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
final class DatabaseInitialization {

    @Inject
    private UserDAO userDAO;

    @Inject
    private FeatureDAO featureDAO;

    @Inject
    private FederationDAO federationDAO;

    void init() {

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

        final Feature missing = new Feature();
        missing.setToken("*");
        missing.setGrantType(GrantType.BOTH);

        final Feature contacts = new Feature();
        contacts.setToken("contacts");
        contacts.setGrantType(GrantType.AUTHENTICATED_ONLY);

        final Feature contact = new Feature();
        contact.setToken("contact");
        contact.setGrantType(GrantType.AUTHENTICATED_ONLY);

        final Feature associations = new Feature();
        associations.setToken("associations");
        associations.setGrantType(GrantType.AUTHENTICATED_ONLY);

        final Feature association = new Feature();
        association.setToken("association");
        association.setGrantType(GrantType.AUTHENTICATED_ONLY);

        final Feature users = new Feature();
        users.setToken("users");
        users.setGrantType(GrantType.AUTHENTICATED_ONLY);

        final Feature user = new Feature();
        user.setToken("user");
        user.setGrantType(GrantType.AUTHENTICATED_ONLY);

        featureDAO.persist(missing, null);
        featureDAO.persist(contacts, null);
        featureDAO.persist(contact, null);
        featureDAO.persist(associations, null);
        featureDAO.persist(association, null);
        featureDAO.persist(users, null);
        featureDAO.persist(user, null);

        // --
        // STRUCTURES.
        // --

        final Federation federation = new Federation();
        federation.setName("Fédération nationale");

        federationDAO.persist(federation, null);
    }

}