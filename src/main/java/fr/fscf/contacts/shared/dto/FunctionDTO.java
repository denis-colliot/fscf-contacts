package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.shared.dto.base.AbstractEntityDTO;

import javax.validation.constraints.NotNull;

/**
 * Created on 27/11/16.
 *
 * @author Denis
 */
public class FunctionDTO extends AbstractEntityDTO<Long> {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
