package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.StructureType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Association model.
 */
@javax.persistence.Entity
@DiscriminatorValue(StructureType.ASSOCIATION)
public class Association extends Structure {

    /**
     * Parent entity.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "parent_id", nullable = true)
    private Comity parent;

    public Association() {
    }

    public Association(Long id) {
        super(id);
    }

    public Comity getParent() {
        return parent;
    }

    public void setParent(Comity parent) {
        this.parent = parent;
    }
}
