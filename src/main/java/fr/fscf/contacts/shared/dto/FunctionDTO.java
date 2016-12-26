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

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
