package fr.fscf.contacts.server.dao;

import fr.fscf.contacts.server.model.Association;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Denis on 25/04/15.
 */
public class AssociationDAOTest extends AbstractDAOTest {

    @Inject
    private AssociationDAO associationDAO;

    @Test
    public void insert() {
        final String name = "Mon association";
        final Association association = new Association();
        association.setName(name);
        association.setEmail("email@association.com");

        assertThat(association.getId()).isNull();

        associationDAO.persist(association, null);

        assertThat(association.getId()).isNotNull();
        assertThat(association.getName()).isEqualTo(name);
    }

    @Test
    public void find() {
        final List<Association> associations = associationDAO.find(null);

        assertThat(associations).isNotNull();
        assertThat(associations.size()).isEqualTo(1);
        assertThat(associations.get(0).getEmail()).isEqualTo("email@association.com");
    }

}
