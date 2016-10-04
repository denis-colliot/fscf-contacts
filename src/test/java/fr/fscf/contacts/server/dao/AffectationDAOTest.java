package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.Affectation;
import fr.fscf.contacts.server.model.AffectationPK;
import fr.fscf.contacts.server.model.referential.AffectationStatus;
import org.junit.Test;

import javax.inject.Inject;

import static fr.fscf.contacts.server.inject.TestDatabaseInitialization.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AffectationDAOTest extends AbstractDAOTest {

    @Inject
    private AffectationDAO affectationDAO;

    @Test
    public void insert() {
        Affectation affectation = new Affectation();
        affectation.setDetailedFunction("Detailed function label");
        affectation.setStatus(AffectationStatus.BENEVOLE);

        affectation.setContact(albert);
        affectation.setStructure(federation);
        affectation.setFunction(federation_president);

        affectationDAO.persist(affectation, null);

        assertThat(affectation.getId().getContactId()).isEqualTo(affectation.getContact().getId());
        assertThat(affectation.getId().getStructureId()).isEqualTo(affectation.getStructure().getId());
        assertThat(affectation.getId().getFunctionId()).isEqualTo(affectation.getFunction().getId());
    }

    @Test
    public void find() {
        final AffectationPK key = new AffectationPK(albert.getId(), association.getId(), regional_league_president.getId());
        Affectation affectation = affectationDAO.findById(key);
        assertThat(affectation).isNotNull();
        assertThat(affectation.getId()).isEqualTo(key);
        assertThat(affectation.getContact().getId()).isEqualTo(albert.getId());
        assertThat(affectation.getContact().getFirstName()).isEqualTo(albert.getFirstName());
    }

}
