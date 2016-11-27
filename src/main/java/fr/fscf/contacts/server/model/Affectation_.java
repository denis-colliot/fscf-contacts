package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.referential.AffectationStatus;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * {@link Affectation} meta-model.
 *
 * @author Denis
 */
@StaticMetamodel(Affectation.class)
public final class Affectation_ {

    public static volatile SingularAttribute<Affectation, AffectationPK> id;
    public static volatile SingularAttribute<Affectation, Contact> contact;
    public static volatile SingularAttribute<Affectation, Structure> structure;
    public static volatile SingularAttribute<Affectation, Function> function;
    public static volatile SingularAttribute<Affectation, String> detailedFunction;
    public static volatile SingularAttribute<Affectation, AffectationStatus> status;

    public static volatile SingularAttribute<Affectation, Date> creationDate;
    public static volatile SingularAttribute<Affectation, String> creationUser;
    public static volatile SingularAttribute<Affectation, Date> updateDate;
    public static volatile SingularAttribute<Affectation, String> updateUser;

    private Affectation_() {
        // Only provides static constants.
    }

}