package fr.fscf.contacts.server.inject.init;

import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.server.config.Configuration;
import fr.fscf.contacts.server.dao.*;
import fr.fscf.contacts.server.model.*;
import fr.fscf.contacts.server.model.referential.GrantType;
import fr.fscf.contacts.server.security.impl.SecuredResources;
import fr.fscf.contacts.shared.command.GetConfigCommand;
import org.apache.commons.lang3.BooleanUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import static fr.fscf.contacts.server.model.referential.AffectationStatus.*;

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
    private RegionalLeagueDAO regionalLeagueDAO;

    @Inject
    private ComityDAO comityDAO;

    @Inject
    private ContactDAO contactDAO;

    @Inject
    private HabilitationDAO habilitationDAO;

    @Inject
    private AffectationDAO affectationDAO;

    @Inject
    private FunctionDAO functionDAO;

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

        final RegionalLeague regionalLeagueBZH = new RegionalLeague();
        regionalLeagueBZH.setName("Ligue de Bretagne");
        regionalLeagueBZH.setParent(federation);

        final Comity comity29 = new Comity();
        comity29.setName("Comité du Finistère");
        comity29.setParent(regionalLeagueBZH);

        final Comity comity35 = new Comity();
        comity35.setName("Comité d'Ille-et-Vilaine");
        comity35.setParent(regionalLeagueBZH);

        final Comity comity56 = new Comity();
        comity56.setName("Comité du Morbihan");
        comity56.setParent(regionalLeagueBZH);

        final RegionalLeague regionalLeagueIDF = new RegionalLeague();
        regionalLeagueIDF.setName("Ligue d'Ile De France");
        regionalLeagueIDF.setParent(federation);

        final Comity comity75 = new Comity();
        comity75.setName("Comité de Paris");
        comity75.setParent(regionalLeagueIDF);

        final Comity comity92 = new Comity();
        comity92.setName("Comité des Hauts de Seine");
        comity92.setParent(regionalLeagueIDF);

        federationDAO.persist(federation, null);

        regionalLeagueDAO.persist(regionalLeagueBZH, null);
        regionalLeagueDAO.persist(regionalLeagueIDF, null);

        comityDAO.persist(comity29, null);
        comityDAO.persist(comity35, null);
        comityDAO.persist(comity56, null);
        comityDAO.persist(comity75, null);
        comityDAO.persist(comity92, null);

        // --
        // CONTACTS.
        // --

        final Contact john = new Contact();
        john.setName("Doe");
        john.setFirstName("John");
        john.setEmail("john.doe@unknown.com");

        final Contact albert = new Contact();
        albert.setName("Enstein");
        albert.setFirstName("Albert");
        albert.setEmail("albert.enstein@relativity.com");

        final Contact frank = new Contact();
        frank.setName("Frank");
        frank.setFirstName("Sinatra");
        frank.setEmail("frank.sinatra@american.com");

        final Contact leonardo = new Contact();
        leonardo.setName("Leonardo");
        leonardo.setFirstName("DiCaprio");
        leonardo.setEmail("leonardo.dicaprio@revenant.com");

        final Contact al = new Contact();
        al.setName("Al");
        al.setFirstName("Pacino");
        al.setEmail("al.pacino@scarface.com");

        contactDAO.persist(john, null);
        contactDAO.persist(albert, null);
        contactDAO.persist(frank, null);
        contactDAO.persist(leonardo, null);
        contactDAO.persist(al, null);

        // --
        // FUNCTIONS.
        // --

        final Function pratiquant = new Function();
        pratiquant.setName("Pratiquant(e)");

        final Function federation_president = new Function();
        federation_president.setName("Président(e) de la fédération");

        final Function regional_league_president = new Function();
        regional_league_president.setName("Président(e) de ligue régionale");

        final Function departemental_committee_president = new Function();
        departemental_committee_president.setName("Président(e) de comité départemental");

        final Function assocation_president = new Function();
        assocation_president.setName("Président(e) d'association");

        final Function developmentAgent = new Function();
        developmentAgent.setName("Agent de développement");

        final Function practice_referent = new Function();
        practice_referent.setName("Référent(e) des pratiques artistiques et culturelles");

        final Function section_responsible = new Function();
        section_responsible.setName("Responsable de section");

        functionDAO.persist(pratiquant, null);
        functionDAO.persist(federation_president, null);
        functionDAO.persist(regional_league_president, null);
        functionDAO.persist(departemental_committee_president, null);
        functionDAO.persist(assocation_president, null);
        functionDAO.persist(developmentAgent, null);
        functionDAO.persist(practice_referent, null);
        functionDAO.persist(section_responsible, null);

        // --
        // AFFECTATIONS.
        // --

        affectationDAO.persist(new Affectation(al, federation, federation_president, SALARIE), null);
        affectationDAO.persist(new Affectation(leonardo, regionalLeagueBZH, regional_league_president, SALARIE), null);
        affectationDAO.persist(new Affectation(john, comity29, departemental_committee_president, SALARIE), null);
        affectationDAO.persist(new Affectation(albert, comity29, section_responsible, SALARIE), null);
        affectationDAO.persist(new Affectation(frank, comity35, departemental_committee_president, SALARIE), null);

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