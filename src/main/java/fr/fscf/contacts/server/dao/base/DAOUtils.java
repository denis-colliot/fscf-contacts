package fr.fscf.contacts.server.dao.base;

/**
 * DAO Utils.
 *
 * @author Denis
 */
public final class DAOUtils {

    public static final String RECURSIVE_STRUCTURES =
            "WITH RECURSIVE structures_tree AS ( " +
                    "  SELECT root_structure.* " +
                    "  FROM " +
                    "    t_structure_st AS root_structure " +
                    "    JOIN t_habilitation_ha AS ha on ha.st_id = root_structure.st_id " +
                    "    JOIN t_fonctionnalite_fe AS fe on fe.fe_id = ha.fe_id " +
                    "  WHERE " +
                    "    ha.ut_id = :userId and fe.fe_token = :featureToken " +
                    " UNION ALL " +
                    "  SELECT child_structure.* " +
                    "  FROM " +
                    "    structures_tree AS parent_structure," +
                    "    t_structure_st AS child_structure " +
                    "  WHERE " +
                    "    child_structure.parent_id = parent_structure.st_id )";

}
