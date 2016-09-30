package fr.fscf.contacts.server.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * {@link Habilitation} meta-model.
 *
 * @author Denis
 */
@StaticMetamodel(Habilitation.class)
public final class Habilitation_ {

    public static volatile SingularAttribute<Habilitation, AffectationPK> id;
    public static volatile SingularAttribute<Habilitation, User> user;
    public static volatile SingularAttribute<Habilitation, Feature> feature;
    public static volatile SingularAttribute<Habilitation, Structure> structure;

    public static volatile SingularAttribute<Habilitation, Date> creationDate;
    public static volatile SingularAttribute<Habilitation, String> creationUser;
    public static volatile SingularAttribute<Habilitation, Date> updateDate;
    public static volatile SingularAttribute<Habilitation, String> updateUser;

    private Habilitation_() {
        // Only provides static constants.
    }

}