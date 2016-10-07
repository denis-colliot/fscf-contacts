package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.GrantType;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * {@link Feature} meta-model.
 *
 * @author Denis
 */
@StaticMetamodel(Feature.class)
public final class Feature_ {

    public static volatile SingularAttribute<Feature, Long> id;
    public static volatile SingularAttribute<Feature, String> token;
    public static volatile SingularAttribute<Feature, GrantType> grantType;
    public static volatile ListAttribute<Feature, Habilitation> habilitations;

    public static volatile SingularAttribute<Feature, Date> creationDate;
    public static volatile SingularAttribute<Feature, String> creationUser;
    public static volatile SingularAttribute<Feature, Date> updateDate;
    public static volatile SingularAttribute<Feature, String> updateUser;

    private Feature_() {
        // Only provides static constants.
    }

}