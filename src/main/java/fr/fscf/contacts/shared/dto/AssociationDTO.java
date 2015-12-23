package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.shared.dto.base.AbstractDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created on 22/12/15.
 *
 * @author Denis
 */
public class AssociationDTO extends AbstractDTO {

    @NotNull
    @Size(min = 5)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
