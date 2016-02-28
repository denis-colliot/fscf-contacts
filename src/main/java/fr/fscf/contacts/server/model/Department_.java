package fr.fscf.contacts.server.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;

/**
 * {@link Department} meta-model.
 *
 * @author Denis
 */
@StaticMetamodel(Department.class)
public final class Department_ {

    public static volatile SingularAttribute<Department, Long> id;
    public static volatile SingularAttribute<Department, String> number;
    public static volatile SingularAttribute<Department, String> label;
    public static volatile SingularAttribute<Department, Region> region;

    public static volatile SingularAttribute<Department, Date> creationDate;
    public static volatile SingularAttribute<Department, String> creationUser;
    public static volatile SingularAttribute<Department, Date> updateDate;
    public static volatile SingularAttribute<Department, String> updateUser;

    private Department_() {
        // Only provides static constants.
    }

}