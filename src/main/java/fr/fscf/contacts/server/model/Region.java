package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;

import javax.persistence.*;

import static fr.fscf.contacts.server.model.util.Entities.*;

/**
 * Territorial region.
 */
@Entity
@Table(name = "t_region_re")
public class Region extends AbstractEntity<Long> {

    // GenerationType.AUTO does not seem to work properly with H2 test database.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = HIBERNATE_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @Column(name = REGION_ID)
    private Long id;

    @Column(name = "re_numero", nullable = false, unique = true)
    private String number;

    @Column(name = "re_libelle", nullable = false)
    private String label;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
