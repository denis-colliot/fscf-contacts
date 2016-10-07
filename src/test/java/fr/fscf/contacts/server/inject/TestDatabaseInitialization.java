package fr.fscf.contacts.server.inject;

import com.google.inject.persist.PersistService;
import fr.fscf.contacts.server.dao.*;
import fr.fscf.contacts.server.model.*;
import fr.fscf.contacts.server.model.referential.GrantType;

import javax.inject.Inject;
import javax.inject.Singleton;

import static fr.fscf.contacts.server.model.referential.AffectationStatus.BENEVOLE;

@Singleton
public final class TestDatabaseInitialization {

    public static final User
            ironman,
            batman,
            superman;

    public static final Feature
            missing,
            feature_1,
            feature_2,
            feature_3,
            feature_4,
            feature_5,
            contacts;

    public static final Federation
            federation;

    public static final Association
            association;

    public static final Contact
            john,
            albert,
            frank,
            leonardo,
            al;

    public static final Function
            pratiquant,
            federation_president,
            regional_league_president,
            departemental_committee_president,
            assocation_president,
            developmentAgent,
            practice_referent,
            section_responsible;

    static {

        // --
        // FEATURES.
        // --

        missing = new Feature();
        missing.setToken("*");
        missing.setGrantType(GrantType.BOTH);

        feature_1 = new Feature();
        feature_1.setToken("feature_1");
        feature_1.setGrantType(GrantType.BOTH);

        feature_2 = new Feature();
        feature_2.setToken("feature_2");
        feature_2.setGrantType(GrantType.BOTH);

        feature_3 = new Feature();
        feature_3.setToken("feature_3");
        feature_3.setGrantType(GrantType.BOTH);

        feature_4 = new Feature();
        feature_4.setToken("feature_4");
        feature_4.setGrantType(GrantType.BOTH);

        feature_5 = new Feature();
        feature_5.setToken("feature_5");
        feature_5.setGrantType(GrantType.BOTH);

        contacts = new Feature();
        contacts.setToken("contacts");
        contacts.setGrantType(GrantType.BOTH);

        // --
        // STRUCTURES.
        // --

        federation = new Federation();
        federation.setName("Fédération nationale");

        association = new Association();
        association.setName("Mon association");
        association.setEmail("email@association.com");

        // --
        // USERS.
        // --

        ironman = new User();
        ironman.setName("Stark");
        ironman.setFirstName("Tony");
        ironman.setEmail("tony@starkindustries.com");
        ironman.setPassword("ironman");
        ironman.setActive(true);

        batman = new User();
        batman.setName("Wayne");
        batman.setFirstName("Bruce");
        batman.setEmail("bruce@wayneindustries.com");
        batman.setPassword("batman");
        batman.setActive(true);

        superman = new User();
        superman.setName("Kent");
        superman.setFirstName("Clark");
        superman.setEmail("clark.kent@dailyplanet.com");
        superman.setPassword("superman");
        superman.setActive(true);

        // --
        // CONTACTS.
        // --

        john = new Contact();
        john.setName("Doe");
        john.setFirstName("John");
        john.setEmail("john.doe@unknown.com");

        albert = new Contact();
        albert.setName("Enstein");
        albert.setFirstName("Albert");
        albert.setEmail("albert.enstein@relativity.com");

        frank = new Contact();
        frank.setName("Frank");
        frank.setFirstName("Sinatra");
        frank.setEmail("frank.sinatra@american.com");

        leonardo = new Contact();
        leonardo.setName("Leonardo");
        leonardo.setFirstName("DiCaprio");
        leonardo.setEmail("leonardo.dicaprio@revenant.com");

        al = new Contact();
        al.setName("Al");
        al.setFirstName("Pacino");
        al.setEmail("al.pacino@scarface.com");

        // --
        // FUNCTIONS.
        // --

        pratiquant = new Function();
        pratiquant.setName("Pratiquant(e)");

        federation_president = new Function();
        federation_president.setName("Président(e) de la fédération");

        regional_league_president = new Function();
        regional_league_president.setName("Président(e) de ligue régionale");

        departemental_committee_president = new Function();
        departemental_committee_president.setName("Président(e) de comité départemental");

        assocation_president = new Function();
        assocation_president.setName("Président(e) d'association");

        developmentAgent = new Function();
        developmentAgent.setName("Agent de développement");

        practice_referent = new Function();
        practice_referent.setName("Référent(e) des pratiques artistiques et culturelles");

        section_responsible = new Function();
        section_responsible.setName("Responsable de section");

    }

    @Inject
    public TestDatabaseInitialization(final PersistService service,
                                      final UserDAO userDAO,
                                      final ContactDAO contactDAO,
                                      final FunctionDAO functionDAO,
                                      final FeatureDAO featureDAO,
                                      final AffectationDAO affectationDAO,
                                      final HabilitationDAO habilitationDAO,
                                      final FederationDAO federationDAO,
                                      final AssociationDAO associationDAO) {

        service.start(); // CRUCIAL.

        final User initializationAuthor = null;

        featureDAO.persist(missing, initializationAuthor);
        featureDAO.persist(feature_1, initializationAuthor);
        featureDAO.persist(feature_2, initializationAuthor);
        featureDAO.persist(feature_3, initializationAuthor);
        featureDAO.persist(feature_4, initializationAuthor);
        featureDAO.persist(feature_5, initializationAuthor);
        featureDAO.persist(contacts, initializationAuthor);

        federationDAO.persist(federation, initializationAuthor);
        associationDAO.persist(association, initializationAuthor);

        userDAO.persist(ironman, initializationAuthor);
        userDAO.persist(batman, initializationAuthor);
        userDAO.persist(superman, initializationAuthor);

        contactDAO.persist(john, initializationAuthor);
        contactDAO.persist(albert, initializationAuthor);
        contactDAO.persist(frank, initializationAuthor);
        contactDAO.persist(leonardo, initializationAuthor);
        contactDAO.persist(al, initializationAuthor);

        functionDAO.persist(pratiquant, initializationAuthor);
        functionDAO.persist(federation_president, initializationAuthor);
        functionDAO.persist(regional_league_president, initializationAuthor);
        functionDAO.persist(departemental_committee_president, initializationAuthor);
        functionDAO.persist(assocation_president, initializationAuthor);
        functionDAO.persist(developmentAgent, initializationAuthor);
        functionDAO.persist(practice_referent, initializationAuthor);
        functionDAO.persist(section_responsible, initializationAuthor);

        // FOREIGN KEYS.

        habilitationDAO.persist(new Habilitation(ironman, feature_1, association), initializationAuthor);
        habilitationDAO.persist(new Habilitation(ironman, feature_2, association), initializationAuthor);
        habilitationDAO.persist(new Habilitation(ironman, feature_3, association), initializationAuthor);
        habilitationDAO.persist(new Habilitation(ironman, contacts, association), initializationAuthor);
        habilitationDAO.persist(new Habilitation(batman, feature_1, association), initializationAuthor);
        habilitationDAO.persist(new Habilitation(batman, feature_2, association), initializationAuthor);

        affectationDAO.persist(new Affectation(albert, association, regional_league_president, BENEVOLE), initializationAuthor);
    }

}