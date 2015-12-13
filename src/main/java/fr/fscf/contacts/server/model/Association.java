package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.EntityType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Association model.
 */
@javax.persistence.Entity
@DiscriminatorValue(EntityType.ASSOCIATION)
public class Association extends Entity {

    /**
     * Parent entity.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "parent_id", nullable = true)
    private Comity parent;

    public Comity getParent() {
        return parent;
    }

    public void setParent(Comity parent) {
        this.parent = parent;
    }
}
