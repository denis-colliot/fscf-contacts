package fr.fscf.contacts.server.inject.init;

import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.server.config.Configuration;
import fr.fscf.contacts.server.dao.*;
import fr.fscf.contacts.server.model.*;
import fr.fscf.contacts.server.model.referential.GrantType;
import fr.fscf.contacts.server.security.impl.SecuredResources;
import fr.fscf.contacts.shared.command.GetConfigCommand;
import fr.fscf.contacts.shared.dto.referential.StructureType;
import org.apache.commons.lang3.BooleanUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import static fr.fscf.contacts.server.model.referential.AffectationStatus.SALARIE;
import static fr.fscf.contacts.shared.dto.referential.StructureType.*;

/**
 * Initializes the application database with default set of data.
 *
 * @author Denis
 * @see InitializationFilter
 */
@Singleton
final class DatabaseInitialization {

    /**
     * User p4ssw0rd.
     */
    private static final String USER_PWD = "$2a$10$sZ0Xr5EaDM6JWpnkis1bDuLJOop6vpaMolCvOYJhEpPcidu0tkXp6";

    // --
    // FEATURES.
    // --

    private static final Feature missing = new Feature(SecuredResources.MISSING_TOKEN, GrantType.BOTH);
    private static final Feature contacts = new Feature(Page.CONTACTS, GrantType.AUTHENTICATED_ONLY);
    private static final Feature contact = new Feature(Page.CONTACT, GrantType.AUTHENTICATED_ONLY);
    private static final Feature associations = new Feature(Page.ASSOCIATIONS, GrantType.AUTHENTICATED_ONLY);
    private static final Feature association = new Feature(Page.ASSOCIATION, GrantType.AUTHENTICATED_ONLY);
    private static final Feature users = new Feature(Page.USERS, GrantType.AUTHENTICATED_ONLY);
    private static final Feature user = new Feature(Page.USER, GrantType.AUTHENTICATED_ONLY);
    private static final Feature getConfig = new Feature(GetConfigCommand.class, GrantType.BOTH);

    @Inject
    private Configuration configuration;

    @Inject
    private UserDAO userDAO;

    @Inject
    private FeatureDAO featureDAO;

    @Inject
    private FederationDAO federationDAO;

    @Inject
    private RegionalCommitteeDAO regionalCommitteeDAO;

    @Inject
    private DepartmentalCommitteeDAO departmentalCommitteeDAO;

    @Inject
    private ContactDAO contactDAO;

    @Inject
    private HabilitationDAO habilitationDAO;

    @Inject
    private AffectationDAO affectationDAO;

    @Inject
    private FunctionDAO functionDAO;

    @Inject
    private FunctionStructureTypeDAO functionStructureTypeDAO;

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
        // USERS (persisted with habilitations).
        // --

        final User denis = new User();
        denis.setName("Colliot");
        denis.setFirstName("Denis");
        denis.setEmail("denis.colliot@gmail.com");
        denis.setPassword(USER_PWD);
        denis.setActive(true);

        final User sebastien = new User();
        sebastien.setName("Bouvet");
        sebastien.setFirstName("Sébastien");
        sebastien.setEmail("bouvet.sebastien@gmail.com");
        sebastien.setPassword(USER_PWD);
        sebastien.setActive(true);

        final User federationUser = new User();
        federationUser.setName("FSCF");
        federationUser.setFirstName("Fédération");
        federationUser.setEmail("federation@fscf.fr");
        federationUser.setPassword(USER_PWD);
        federationUser.setActive(true);

        final User regCommitteeBzhUser = new User();
        regCommitteeBzhUser.setName("Bretagne");
        regCommitteeBzhUser.setFirstName("Comité Régional");
        regCommitteeBzhUser.setEmail("comite.regional.bzh@fscf.fr");
        regCommitteeBzhUser.setPassword(USER_PWD);
        regCommitteeBzhUser.setActive(true);

        final User dptCommittee35User = new User();
        dptCommittee35User.setName("Ille-et-Vilaine");
        dptCommittee35User.setFirstName("Comité Départemental");
        dptCommittee35User.setEmail("comite.departemental.35@fscf.fr");
        dptCommittee35User.setPassword(USER_PWD);
        dptCommittee35User.setActive(true);

        final User dptCommittee22User = new User();
        dptCommittee22User.setName("Côtes-d'Armor");
        dptCommittee22User.setFirstName("Comité Départemental");
        dptCommittee22User.setEmail("comite.departemental.22@fscf.fr");
        dptCommittee22User.setPassword(USER_PWD);
        dptCommittee22User.setActive(true);

        final User dptCommittee29User = new User();
        dptCommittee29User.setName("Finistère");
        dptCommittee29User.setFirstName("Comité Départemental");
        dptCommittee29User.setEmail("comite.departemental.29@fscf.fr");
        dptCommittee29User.setPassword(USER_PWD);
        dptCommittee29User.setActive(true);

        final User dptCommittee56User = new User();
        dptCommittee56User.setName("Morbihan");
        dptCommittee56User.setFirstName("Comité Départemental");
        dptCommittee56User.setEmail("comite.departemental.56@fscf.fr");
        dptCommittee56User.setPassword(USER_PWD);
        dptCommittee56User.setActive(true);

        // --
        // FEATURES.
        // --

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
        federation.setName("Fédération Sportive et Culturelle de France");
        federation.setEmail("fscf@fscf.asso.fr");
        federation.setPhone("0143385057");
        federation.setWebsite("www.fscf.asso.fr");
        federation.setAddress("22 rue Oberkampf");
        federation.setZipCode("75011");
        federation.setCity("PARIS");

        final RegionalCommittee regCommitteeBzh = new RegionalCommittee();
        regCommitteeBzh.setParent(federation);
        regCommitteeBzh.setName("Comité régional Bretagne");
        regCommitteeBzh.setEmail("coordination@fscf-bretagne.fr");
        regCommitteeBzh.setPhone("0299503811");
        regCommitteeBzh.setWebsite("www.fscf-bretagne.fr");
        regCommitteeBzh.setAddress("BP 10303");
        regCommitteeBzh.setZipCode("35203");
        regCommitteeBzh.setCity("Rennes");
        regCommitteeBzh.setCedex("Cedex 2");

        final DepartmentalCommittee dptCommittee29 = new DepartmentalCommittee();
        dptCommittee29.setParent(regCommitteeBzh);
        dptCommittee29.setName("Comité départemental Finistère");
        dptCommittee29.setWebsite("jla29@orange.fr");

        final DepartmentalCommittee dptCommittee35 = new DepartmentalCommittee();
        dptCommittee35.setParent(regCommitteeBzh);
        dptCommittee35.setName("Comité départemental Ille-et-Vilaine");
        dptCommittee35.setWebsite("fscf.cd35@wanadoo.fr");

        final DepartmentalCommittee dptCommittee22 = new DepartmentalCommittee();
        dptCommittee22.setParent(regCommitteeBzh);
        dptCommittee22.setName("Comité départemental Côtes-d'Armor");
        dptCommittee22.setWebsite("fscfcd22@orange.fr");

        final DepartmentalCommittee dptCommittee56 = new DepartmentalCommittee();
        dptCommittee56.setParent(regCommitteeBzh);
        dptCommittee56.setName("Comité départemental Morbihan");
        dptCommittee56.setWebsite("cd56.fscf@wanadoo.fr");

        final RegionalCommittee regCommitteeIdf = new RegionalCommittee();
        regCommitteeIdf.setParent(federation);
        regCommitteeIdf.setName("Ligue d'Ile De France");

        final DepartmentalCommittee dptCommittee75 = new DepartmentalCommittee();
        dptCommittee75.setParent(regCommitteeIdf);
        dptCommittee75.setName("Comité de Paris");

        final DepartmentalCommittee dptCommittee92 = new DepartmentalCommittee();
        dptCommittee92.setParent(regCommitteeIdf);
        dptCommittee92.setName("Comité des Hauts de Seine");

        federationDAO.persist(federation, null);

        regionalCommitteeDAO.persist(regCommitteeBzh, null);
        regionalCommitteeDAO.persist(regCommitteeIdf, null);

        departmentalCommitteeDAO.persist(dptCommittee29, null);
        departmentalCommitteeDAO.persist(dptCommittee35, null);
        departmentalCommitteeDAO.persist(dptCommittee22, null);
        departmentalCommitteeDAO.persist(dptCommittee56, null);
        departmentalCommitteeDAO.persist(dptCommittee75, null);
        departmentalCommitteeDAO.persist(dptCommittee92, null);

        // --
        // CONTACTS.
        // --

        final Contact contact01_federation = new Contact();
        contact01_federation.setFirstName("Contact01");
        contact01_federation.setName("Fédération");
        contact01_federation.setEmail("contact01@federation.com");

        final Contact contact01_regCommitteeBzh = new Contact();
        contact01_regCommitteeBzh.setFirstName("Contact01");
        contact01_regCommitteeBzh.setName("ComitéRegBzh");
        contact01_regCommitteeBzh.setEmail("contact01@comiteRegBzh.fr");

        final Contact contact01_dptCommittee29 = new Contact();
        contact01_dptCommittee29.setFirstName("Contact01");
        contact01_dptCommittee29.setName("ComitéDpt29");
        contact01_dptCommittee29.setEmail("contact01@comiteDpt29.fr");

        final Contact contact02_dptCommittee29 = new Contact();
        contact02_dptCommittee29.setFirstName("Contact02");
        contact02_dptCommittee29.setName("ComitéDpt29");
        contact02_dptCommittee29.setEmail("contact02@comiteDpt29.fr");

        final Contact contact01_dptCommittee35 = new Contact();
        contact01_dptCommittee35.setFirstName("Contact01");
        contact01_dptCommittee35.setName("ComitéDpt35");
        contact01_dptCommittee35.setEmail("contact01@comiteDpt35.fr");

        contactDAO.persist(contact01_federation, null);
        contactDAO.persist(contact01_regCommitteeBzh, null);
        contactDAO.persist(contact01_dptCommittee29, null);
        contactDAO.persist(contact02_dptCommittee29, null);
        contactDAO.persist(contact01_dptCommittee35, null);

        // --
        // FUNCTIONS.
        // --

        final Function regional_committee_president = new Function();
        regional_committee_president.setName("Président(e) de comité régional");

        final Function departmental_committee_president = new Function();
        departmental_committee_president.setName("Président(e) de comité départemental");

        final Function pratiquant = new Function();
        pratiquant.setName("Pratiquant(e)");

        final Function section_responsible = new Function();
        section_responsible.setName("Responsable de section");

        final Function association_president = new Function();
        association_president.setName("Président(e) d'association");

        final Function practice_referent = new Function();
        practice_referent.setName("Référent(e) des pratiques artistiques et culturelles");

        final Function development_agent = new Function();
        development_agent.setName("Agent de développement");

        final Function federal_headquarter_employee = new Function();
        federal_headquarter_employee.setName("Salarié(e) du siège fédéral");

        final Function activity_teacher = new Function();
        activity_teacher.setName("Animateur(trice) d'activité");

        final Function national_commission_member = new Function();
        national_commission_member.setName("Membre de commission nationale");

        final Function regional_committee_member = new Function();
        regional_committee_member.setName("Membre de comité régional");

        final Function departmental_committee_member = new Function();
        departmental_committee_member.setName("Membre de comité départemental");

        final Function federation_president = new Function();
        federation_president.setName("Président(e) de la fédération");

        functionDAO.persist(pratiquant, null);
        functionDAO.persist(federation_president, null);
        functionDAO.persist(regional_committee_president, null);
        functionDAO.persist(departmental_committee_president, null);
        functionDAO.persist(association_president, null);
        functionDAO.persist(development_agent, null);
        functionDAO.persist(practice_referent, null);
        functionDAO.persist(section_responsible, null);
        functionDAO.persist(federal_headquarter_employee, null);
        functionDAO.persist(regional_committee_member, null);
        functionDAO.persist(activity_teacher, null);
        functionDAO.persist(national_commission_member, null);
        functionDAO.persist(departmental_committee_member, null);

        // --
        // AFFECTATIONS.
        // --

        affectationDAO.persist(new Affectation(contact01_federation, federation, federation_president, SALARIE), null);
        affectationDAO.persist(new Affectation(contact01_regCommitteeBzh, regCommitteeBzh, regional_committee_president, SALARIE), null);
        affectationDAO.persist(new Affectation(contact01_dptCommittee29, dptCommittee29, departmental_committee_president, SALARIE), null);
        affectationDAO.persist(new Affectation(contact02_dptCommittee29, dptCommittee29, section_responsible, SALARIE), null);
        affectationDAO.persist(new Affectation(contact01_dptCommittee35, dptCommittee35, departmental_committee_president, SALARIE), null);
        affectationDAO.persist(new Affectation(contact01_dptCommittee35, dptCommittee35, practice_referent, SALARIE), null);

        // --
        // HABILITATIONS.
        // --

        persistHabilitations(denis, federation);
        persistHabilitations(sebastien, federation);
        persistHabilitations(federationUser, federation);
        persistHabilitations(regCommitteeBzhUser, regCommitteeBzh);
        persistHabilitations(dptCommittee35User, dptCommittee35);
        persistHabilitations(dptCommittee22User, dptCommittee22);
        persistHabilitations(dptCommittee29User, dptCommittee29);
        persistHabilitations(dptCommittee56User, dptCommittee56);

        // --
        // FONCTIONS - TYPES DE STRUCTURE.
        // --

        functionStructureTypeDAO.persist(new FunctionStructureType(COMITE_REGIONAL, regional_committee_president), null);
        functionStructureTypeDAO.persist(new FunctionStructureType(COMITE_REGIONAL, regional_committee_member), null);
        functionStructureTypeDAO.persist(new FunctionStructureType(COMITE_DEPARTEMENTAL, departmental_committee_president), null);
        functionStructureTypeDAO.persist(new FunctionStructureType(COMITE_DEPARTEMENTAL, departmental_committee_member), null);
        functionStructureTypeDAO.persist(new FunctionStructureType(FEDERATION, federation_president), null); // TODO A confirmer
        functionStructureTypeDAO.persist(new FunctionStructureType(FEDERATION, federal_headquarter_employee), null); // TODO A confirmer
        functionStructureTypeDAO.persist(new FunctionStructureType(ASSOCIATION, pratiquant), null);
        functionStructureTypeDAO.persist(new FunctionStructureType(ASSOCIATION, section_responsible), null);
        functionStructureTypeDAO.persist(new FunctionStructureType(ASSOCIATION, practice_referent), null); // TODO
        functionStructureTypeDAO.persist(new FunctionStructureType(ASSOCIATION, development_agent), null); // TODO
        functionStructureTypeDAO.persist(new FunctionStructureType(ASSOCIATION, association_president), null); // TODO
        functionStructureTypeDAO.persist(new FunctionStructureType(ASSOCIATION, activity_teacher), null); // TODO
        functionStructureTypeDAO.persist(new FunctionStructureType(FEDERATION, national_commission_member), null); // TODO
    }

    private void persistHabilitations(User theUser, Structure structure) {
        userDAO.persist(theUser, null);

        habilitationDAO.persist(new Habilitation(theUser, contact, structure), null);
        habilitationDAO.persist(new Habilitation(theUser, contacts, structure), null);
        habilitationDAO.persist(new Habilitation(theUser, association, structure), null);
        habilitationDAO.persist(new Habilitation(theUser, associations, structure), null);
        habilitationDAO.persist(new Habilitation(theUser, user, structure), null);
        habilitationDAO.persist(new Habilitation(theUser, users, structure), null);
        habilitationDAO.persist(new Habilitation(theUser, contact, structure), null);
        habilitationDAO.persist(new Habilitation(theUser, getConfig, structure), null);
    }

}