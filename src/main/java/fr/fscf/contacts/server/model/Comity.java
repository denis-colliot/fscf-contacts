package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.StructureType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Departmental comity model.
 */
@javax.persistence.Entity
@DiscriminatorValue(StructureType.COMITE_DEPARTEMENTAL)
public class Comity extends Structure {

    /**
     * Parent entity.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "parent_id", nullable = true)
    private RegionalLeague parent;

    public Comity() {
    }

    public Comity(Long id) {
        super(id);
    }

    public RegionalLeague getParent() {
        return parent;
    }

    public void setParent(RegionalLeague parent) {
        this.parent = parent;
    }

}
