package fr.fscf.contacts.shared.dto.referential;

/**
 * Structure types.
 *
 * @author Denis
 */
public enum StructureType {

    FEDERATION(StructureType.FEDERATION_KEY),
    COMMISSION_NATIONALE(StructureType.COMMISSION_NATIONALE_KEY),
    COMITE_REGIONAL(StructureType.COMITE_REGIONAL_KEY),
    COMITE_DEPARTEMENTAL(StructureType.COMITE_DEPARTEMENTAL_KEY),
    ASSOCIATION(StructureType.ASSOCIATION_KEY),
    SECTION(StructureType.SECTION_KEY);

    public static final String FEDERATION_KEY = "FEDERATION";
    public static final String COMMISSION_NATIONALE_KEY = "COMMISSION_NATIONALE";
    public static final String COMITE_REGIONAL_KEY = "COMITE_REGIONAL";
    public static final String COMITE_DEPARTEMENTAL_KEY = "COMITE_DEPARTEMENTAL";
    public static final String ASSOCIATION_KEY = "ASSOCIATION";
    public static final String SECTION_KEY = "SECTION";

    public final String key;

    StructureType(String key) {
        this.key = key;
    }

}
