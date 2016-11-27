package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.shared.dto.base.AbstractEntityDTO;

import javax.validation.constraints.NotNull;

/**
 * Created on 27/11/16.
 *
 * @author Denis
 */
public class FunctionDTO extends AbstractEntityDTO<Long, FunctionDTO> {

    @NotNull
    private String name;

    @Override
    public String render(FunctionDTO function) {
        return function.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
