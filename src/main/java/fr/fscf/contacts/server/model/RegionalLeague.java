package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.StructureType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Regional league model.
 */
@javax.persistence.Entity
@DiscriminatorValue(StructureType.LIGUE_REGIONALE)
public class RegionalLeague extends Structure {

    /**
     * Parent entity.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "parent_id", nullable = true)
    private Federation parent;

    public Federation getParent() {
        return parent;
    }

    public void setParent(Federation parent) {
        this.parent = parent;
    }
}
