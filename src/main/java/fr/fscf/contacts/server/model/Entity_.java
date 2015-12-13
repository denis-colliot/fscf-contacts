package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.EntityType;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * {@link Entity} meta-model.
 *
 * @author Denis
 */
@StaticMetamodel(Entity.class)
public final class Entity_ {

    public static volatile SingularAttribute<Entity, Long> id;
    public static volatile SingularAttribute<Entity, Entity> parent;
    public static volatile SingularAttribute<Entity, String> name;
    public static volatile SingularAttribute<Entity, String> address;
    public static volatile SingularAttribute<Entity, String> additionalAddress;
    public static volatile SingularAttribute<Entity, String> zipCode;
    public static volatile SingularAttribute<Entity, String> city;
    public static volatile SingularAttribute<Entity, String> email;
    public static volatile SingularAttribute<Entity, String> phone;
    public static volatile SingularAttribute<Entity, String> website;

    public static volatile SingularAttribute<Entity, Date> creationDate;
    public static volatile SingularAttribute<Entity, String> creationUser;
    public static volatile SingularAttribute<Entity, Date> updateDate;
    public static volatile SingularAttribute<Entity, String> updateUser;

    private Entity_() {
        // Only provides static constants.
    }

}