package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.StructureType;

import javax.persistence.DiscriminatorValue;

/**
 * Federation model.
 */
@javax.persistence.Entity
@DiscriminatorValue(StructureType.FEDERATION)
public class Federation extends Structure {

    public Federation() {
    }

    public Federation(Long id) {
        super(id);
    }

}
