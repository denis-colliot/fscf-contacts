package fr.fscf.contacts.server.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * {@link Function} meta-model.
 *
 * @author Denis
 */
@StaticMetamodel(Function.class)
public final class Function_ {

    public static volatile SingularAttribute<Function, Long> id;
    public static volatile SingularAttribute<Function, String> name;

    public static volatile SingularAttribute<Function, Date> creationDate;
    public static volatile SingularAttribute<Function, String> creationUser;
    public static volatile SingularAttribute<Function, Date> updateDate;
    public static volatile SingularAttribute<Function, String> updateUser;

    private Function_() {
        // Only provides static constants.
    }

}