package fr.fscf.contacts.server.model.util;

/**
 * Model entities constants.
 *
 * @author Denis
 */
public final class Entities {

    // --
    // Id sequence generator.
    // --

    public static final String SEQUENCE_GENERATOR = "sequenceGenerator";
    public static final String HIBERNATE_SEQUENCE = "hibernate_sequence";
    public static final int ALLOCATION_SIZE = 1;

    // --
    // Id columns.
    // --

    public static final String USER_ID = "ut_id";
    public static final String FEATURE_ID = "fe_id";
    public static final String STRUCTURE_ID = "st_id";
    public static final String REGION_ID = "re_id";
    public static final String DEPARTMENT_ID = "de_id";

    // --
    // Relational tables.
    // --

    public static final String HABILITATION_TABLE = "tr_habilitation_ha";

    private Entities() {
    }
}
