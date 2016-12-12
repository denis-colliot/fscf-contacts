package fr.fscf.contacts.shared.util;

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
    // Common columns.
    // --

    public static final String CREATION_DATE = "creation_date";
    public static final String CREATION_USER = "creation_user";
    public static final String UPDATE_DATE = "update_date";
    public static final String UPDATE_USER = "update_user";

    // --
    // Id columns.
    // --

    public static final String USER_ID = "ut_id";
    public static final String FEATURE_ID = "fe_id";
    public static final String STRUCTURE_ID = "st_id";
    public static final String REGION_ID = "re_id";
    public static final String DEPARTMENT_ID = "de_id";
    public static final String CONTACT_ID = "co_id";
    public static final String FUNCTION_ID = "fo_id";

    // --
    // Relational tables.
    // --

    public static final String STRUCTURE_TYPE = "st_type";
    public static final String STRUCTURE_NAME = "st_nom";
    public static final String STRUCTURE_EMAIL = "st_email";
    public static final String STRUCTURE_PHONE = "st_telephone";
    public static final String STRUCTURE_WEBSITE = "st_site_web";
    public static final String STRUCTURE_ADDRESS = "st_adresse";
    public static final String STRUCTURE_ADDITIONAL_ADDRESS = "st_adresse_compl";
    public static final String STRUCTURE_ZIP_CODE = "st_code_postal";
    public static final String STRUCTURE_CITY = "st_ville";
    public static final String STRUCTURE_CEDEX = "st_cedex";
    public static final String STRUCTURE_EMAIL_2 = "st_email2";
    public static final String STRUCTURE_PHONE_2 = "st_telephone2";
    public static final String STRUCTURE_COMMENTS = "st_commentaire";

    // --
    // Contact table.
    // --

    public static final String CONTACT_NAME = "co_nom";
    public static final String CONTACT_FIRST_NAME = "co_prenom";
    public static final String CONTACT_EMAIL = "co_email";
    public static final String CONTACT_PHONE = "co_telephone";
    public static final String CONTACT_ADDRESS = "co_adresse";
    public static final String CONTACT_ADDITIONAL_ADDRESS = "co_adresse_compl";
    public static final String CONTACT_ZIP_CODE = "co_code_postal";
    public static final String CONTACT_CITY = "co_ville";
    public static final String CONTACT_CEDEX = "co_cedex";
    public static final String CONTACT_EMAIL2 = "co_email2";
    public static final String CONTACT_PHONE2 = "co_telephone2";

    private Entities() {
    }
}
