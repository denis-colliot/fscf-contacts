package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.client.i18n.I18N;
import fr.fscf.contacts.shared.dto.base.AbstractEntityDTO;

import javax.validation.constraints.NotNull;

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

    /**
     * Returns if the given function is the {@link #OTHER} instance.
     *
     * @param function The function, or {@code null}.
     * @return {@code true} is the given function is the {@link #OTHER} instance, {@code false} otherwise.
     */
    public static boolean isOther(final FunctionDTO function) {
        return getOther().equals(function);
    }

    /**
     * Function name.
     */
    @NotNull
    private String name;

    /**
     * Is the function related to association structures?
     */
    private boolean associationFunction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAssociationFunction() {
        return associationFunction;
    }

    public void setAssociationFunction(boolean associationFunction) {
        this.associationFunction = associationFunction;
    }
}
