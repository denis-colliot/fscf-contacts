package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.shared.util.Constraints;
import fr.fscf.contacts.shared.dto.base.AbstractEntityDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created on 22/12/15.
 *
 * @author Denis
 */
public class AssociationDTO extends AbstractEntityDTO<Long, AssociationDTO> {

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    private String address;

    @NotNull
    @Pattern(regexp = Constraints.ZIP_CODE_PATTERN, flags = Pattern.Flag.CASE_INSENSITIVE)
    private String zipCode;

    @Override
    public String render(AssociationDTO association) {
        return association.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
