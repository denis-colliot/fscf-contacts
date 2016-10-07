package fr.fscf.contacts.server.model;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * {@link Structure} meta-model.
 *
 * @author Denis
 */
@StaticMetamodel(Structure.class)
public final class Structure_ {

    public static volatile SingularAttribute<Structure, Long> id;
    public static volatile SingularAttribute<Structure, Structure> parent;
    public static volatile SingularAttribute<Structure, String> name;
    public static volatile SingularAttribute<Structure, String> address;
    public static volatile SingularAttribute<Structure, String> additionalAddress;
    public static volatile SingularAttribute<Structure, String> zipCode;
    public static volatile SingularAttribute<Structure, String> city;
    public static volatile SingularAttribute<Structure, String> cedex;
    public static volatile SingularAttribute<Structure, String> email;
    public static volatile SingularAttribute<Structure, String> email2;
    public static volatile SingularAttribute<Structure, String> phone;
    public static volatile SingularAttribute<Structure, String> phone2;
    public static volatile SingularAttribute<Structure, String> website;
    public static volatile SingularAttribute<Structure, String> comment;

    public static volatile SingularAttribute<Structure, Department> department;
    public static volatile ListAttribute<Structure, Habilitation> habilitations;

    public static volatile SingularAttribute<Structure, Date> creationDate;
    public static volatile SingularAttribute<Structure, String> creationUser;
    public static volatile SingularAttribute<Structure, Date> updateDate;
    public static volatile SingularAttribute<Structure, String> updateUser;

    private Structure_() {
        // Only provides static constants.
    }

}