package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.shared.dto.base.AbstractEntityDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Application contact DTO.
 */
public class ContactDTO extends AbstractEntityDTO<Long> {

    @NotNull
    private String name;

    @NotNull
    private String firstName;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    private String additionalAddress;

    @NotNull
    @Pattern(regexp = "^[2A|2B|\\d{2}]\\d{3}$", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String zipCode;

    @NotNull
    private String city;

    private String cedex;

    private String email2;

    private String phone2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setAdditionalAddress(String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCedex() {
        return cedex;
    }

    public void setCedex(String cedex) {
        this.cedex = cedex;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

}
