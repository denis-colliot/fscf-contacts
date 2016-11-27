package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.GrantType;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * {@link Region} meta-model.
 *
 * @author Denis
 */
@StaticMetamodel(Region.class)
public final class Region_ {

    public static volatile SingularAttribute<Region, Long> id;
    public static volatile SingularAttribute<Region, String> number;
    public static volatile SingularAttribute<Region, String> label;

    public static volatile SingularAttribute<Region, Date> creationDate;
    public static volatile SingularAttribute<Region, String> creationUser;
    public static volatile SingularAttribute<Region, Date> updateDate;
    public static volatile SingularAttribute<Region, String> updateUser;

    private Region_() {
        // Only provides static constants.
    }

}