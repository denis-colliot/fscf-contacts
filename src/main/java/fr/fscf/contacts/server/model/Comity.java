package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.EntityType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Departmental comity model.
 */
@javax.persistence.Entity
@DiscriminatorValue(EntityType.COMITE_DEP)
public class Comity extends Entity {

    /**
     * Parent entity.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "parent_id", nullable = true)
    private RegionalLeague parent;

    public RegionalLeague getParent() {
        return parent;
    }

    public void setParent(RegionalLeague parent) {
        this.parent = parent;
    }

}
