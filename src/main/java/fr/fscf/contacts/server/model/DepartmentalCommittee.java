package fr.fscf.contacts.server.model;

import fr.fscf.contacts.shared.dto.referential.StructureType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Departmental committee model.
 */
@javax.persistence.Entity
@DiscriminatorValue(StructureType.COMITE_DEPARTEMENTAL_KEY)
public class DepartmentalCommittee extends Structure {

    /**
     * Parent entity.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "parent_id", nullable = true)
    private RegionalCommittee parent;

    public DepartmentalCommittee() {
    }

    public DepartmentalCommittee(Long id) {
        super(id);
    }

    @Override
    public StructureType getType() {
        return StructureType.COMITE_DEPARTEMENTAL;
    }

    public RegionalCommittee getParent() {
        return parent;
    }

    public void setParent(RegionalCommittee parent) {
        this.parent = parent;
    }

}
