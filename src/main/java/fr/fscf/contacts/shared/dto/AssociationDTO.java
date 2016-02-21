package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.shared.dto.base.AbstractDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotNull
    private String address;

    @NotNull
    @Pattern(regexp = "^[2A|2B|\\d{2}]\\d{3}$", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String zipCode;

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
