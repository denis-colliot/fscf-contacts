package fr.fscf.contacts.server.model;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * {@link Contact} meta-model.
 *
 * @author Denis
 */
@StaticMetamodel(Contact.class)
public final class Contact_ {

    public static volatile SingularAttribute<Contact, Long> id;
    public static volatile SingularAttribute<Contact, String> name;
    public static volatile SingularAttribute<Contact, String> firstName;
    public static volatile SingularAttribute<Contact, String> email;
    public static volatile SingularAttribute<Contact, String> phone;
    public static volatile SingularAttribute<Contact, String> address;
    public static volatile SingularAttribute<Contact, String> additionalAddress;
    public static volatile SingularAttribute<Contact, String> zipCode;
    public static volatile SingularAttribute<Contact, String> city;
    public static volatile SingularAttribute<Contact, String> cedex;
    public static volatile SingularAttribute<Contact, String> email2;
    public static volatile SingularAttribute<Contact, String> phone2;

    public static volatile ListAttribute<Contact, Affectation> affectations;

    public static volatile SingularAttribute<Contact, Date> creationDate;
    public static volatile SingularAttribute<Contact, String> creationUser;
    public static volatile SingularAttribute<Contact, Date> updateDate;
    public static volatile SingularAttribute<Contact, String> updateUser;

    private Contact_() {
        // Only provides static constants.
    }

}