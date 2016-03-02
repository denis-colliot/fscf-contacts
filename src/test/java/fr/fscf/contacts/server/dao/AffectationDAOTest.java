package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.*;
import fr.fscf.contacts.server.model.referential.AffectationStatus;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Denis on 25/04/15.
 */
public class AffectationDAOTest extends AbstractDAOTest {

    @Inject
    private AffectationDAO affectationDAO;

    @Test
    public void insert() {
        Affectation affectation = new Affectation();
        affectation.setDetailedFunction("Detailed function label");
        affectation.setStatus(AffectationStatus.BENEVOLE);

        affectation.setContact(new Contact(1L));
        affectation.setStructure(new Federation(30L));
        affectation.setFunction(new Function(1L));

        affectationDAO.persist(affectation, null);
    }

    @Test
    public void find() {
        final AffectationPK key = new AffectationPK(2L, 30L, 3L);
        Affectation affectation = affectationDAO.findById(key);
        assertThat(affectation).isNotNull();
        assertThat(affectation.getId()).isEqualTo(key);
        assertThat(affectation.getContact().getId()).isEqualTo(2L);
        assertThat(affectation.getContact().getFirstName()).isEqualTo("Albert");
    }

}
