package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.EntityType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Regional league model.
 */
@javax.persistence.Entity
@DiscriminatorValue(EntityType.LIGUE_REG)
public class RegionalLeague extends Entity {

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
