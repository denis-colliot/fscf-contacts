package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.EntityType;

import javax.persistence.DiscriminatorValue;

/**
 * Federation model.
 */
@javax.persistence.Entity
@DiscriminatorValue(EntityType.FEDERATION)
public class Federation extends Entity {

    // Entity inherited implementation.

}
