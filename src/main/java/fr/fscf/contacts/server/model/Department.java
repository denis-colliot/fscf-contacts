package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;

import javax.persistence.*;

import static fr.fscf.contacts.shared.util.Entities.*;

/**
 * Territorial department.
 */
@Entity
@Table(name = "t_departement_de")
public class Department extends AbstractEntity<Long> {

    // GenerationType.AUTO does not seem to work properly with H2 test database.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = HIBERNATE_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @Column(name = DEPARTMENT_ID)
    private Long id;

    @Column(name = "de_numero", nullable = false, unique = true)
    private String number;

    @Column(name = "de_libelle", nullable = false)
    private String label;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = REGION_ID, nullable = false)
    private Region region;

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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
