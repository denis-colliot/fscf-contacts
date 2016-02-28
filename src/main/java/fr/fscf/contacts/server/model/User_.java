package fr.fscf.contacts.server.model;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import java.util.List;

/**
 * {@link User} meta-model.
 *
 * @author Denis
 */
@StaticMetamodel(User.class)
public final class User_ {

    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> firstName;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile ListAttribute<User, Structure> structures;
    public static volatile ListAttribute<User, Feature> features;

    public static volatile SingularAttribute<User, Date> creationDate;
    public static volatile SingularAttribute<User, String> creationUser;
    public static volatile SingularAttribute<User, Date> updateDate;
    public static volatile SingularAttribute<User, String> updateUser;

    private User_() {
        // Only provides static constants.
    }

}