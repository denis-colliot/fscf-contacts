package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.client.i18n.I18N;
import fr.fscf.contacts.shared.dto.base.AbstractEntityDTO;
import fr.fscf.contacts.shared.dto.referential.StructureType;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 27/11/16.
 *
 * @author Denis
 */
public class FunctionDTO extends AbstractEntityDTO<Long> {

    private static final FunctionDTO OTHER = new FunctionDTO();

    /**
     * Returns the <em>other</em> function choice.
     */
    public static FunctionDTO getOther() {
        OTHER.setId(-9999L);
        OTHER.setName(I18N.CONSTANTS.function_other());
        return OTHER;
    }

    public static boolean isOther(final FunctionDTO function) {
        return getOther().equals(function);
    }

    @NotNull
    private String name;

    /**
     * Structure types related to this function.
     */
    private Set<StructureType> structureTypes;

    /**
     * Adds the given structure type to the current function.
     *
     * @param structureType The structure type (does nothing if {@code null}).
     */
    public void addStructureType(final StructureType structureType) {
        if (structureType == null) {
            return;
        }
        if (structureTypes == null) {
            structureTypes = new HashSet<>();
        }
        structureTypes.add(structureType);
    }

    public boolean hasStructureType(final StructureType structureType) {
        return structureTypes != null && structureTypes.contains(structureType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<StructureType> getStructureTypes() {
        return structureTypes;
    }
}
