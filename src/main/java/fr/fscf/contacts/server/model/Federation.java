package fr.fscf.contacts.server.model;

import fr.fscf.contacts.shared.dto.referential.StructureType;

import javax.persistence.DiscriminatorValue;

/**
 * Federation model.
 */
@javax.persistence.Entity
@DiscriminatorValue(StructureType.FEDERATION_KEY)
public class Federation extends Structure {

    public Federation() {
    }

    public Federation(Long id) {
        super(id);
    }

    @Override
    public StructureType getType() {
        return StructureType.FEDERATION;
    }
}
