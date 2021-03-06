package fr.fscf.contacts.server.model;

import fr.fscf.contacts.shared.dto.referential.StructureType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Regional committee model.
 */
@javax.persistence.Entity
@DiscriminatorValue(StructureType.COMITE_REGIONAL_KEY)
public class RegionalCommittee extends Structure {

    /**
     * Parent entity.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "parent_id", nullable = true)
    private Federation parent;

    public RegionalCommittee() {
    }

    public RegionalCommittee(Long id) {
        super(id);
    }

    @Override
    public StructureType getType() {
        return StructureType.COMITE_REGIONAL;
    }

    public Federation getParent() {
        return parent;
    }

    public void setParent(Federation parent) {
        this.parent = parent;
    }
}
