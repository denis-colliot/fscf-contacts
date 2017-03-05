package fr.fscf.contacts.server.model;

import fr.fscf.contacts.shared.dto.referential.StructureType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Association model.
 */
@javax.persistence.Entity
@DiscriminatorValue(StructureType.ASSOCIATION_KEY)
public class Association extends Structure {

    /**
     * Parent entity.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "parent_id", nullable = true)
    private DepartmentalCommittee parent;

    public Association() {
    }

    public Association(Long id) {
        super(id);
    }

    @Override
    public StructureType getType() {
        return StructureType.ASSOCIATION;
    }

    public DepartmentalCommittee getParent() {
        return parent;
    }

    public void setParent(DepartmentalCommittee parent) {
        this.parent = parent;
    }
}
