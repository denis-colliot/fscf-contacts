package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.shared.dto.base.AbstractEntityDTO;

/**
 * Structure DTO.
 */
public class StructureDTO extends AbstractEntityDTO<Long> {

    private String type;

    private String name;

    private String email;

    private String phone;

    private String website;

    private String address;

    private String additionalAddress;

    private String zipCode;

    private String city;

    private String cedex;

    private String email2;

    private String phone2;

    private String comment;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
