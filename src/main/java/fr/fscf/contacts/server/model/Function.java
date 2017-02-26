package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;

import java.util.List;

import static fr.fscf.contacts.shared.util.Entities.*;

/**
 * Contact function.
 */
@Entity
@Table(name = "t_fonction_fo")
public class Function extends AbstractEntity<Long> {

    // GenerationType.AUTO does not seem to work properly with H2 test database.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = HIBERNATE_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @Column(name = FUNCTION_ID)
    private Long id;

    @Column(name = "fo_nom", nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "function")
    private List<FunctionStructureType> structureTypes;

    public Function() {
    }

    public Function(Long id) {
        this.id = id;
    }

    public boolean hasStructureTypes() {
        return CollectionUtils.isNotEmpty(structureTypes);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FunctionStructureType> getStructureTypes() {
        return structureTypes;
    }

    public void setStructureTypes(List<FunctionStructureType> structureTypes) {
        this.structureTypes = structureTypes;
    }
}
