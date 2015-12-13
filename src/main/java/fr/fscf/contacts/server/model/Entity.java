package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;

import javax.persistence.*;

/**
 * Abstract entity.
 */
@javax.persistence.Entity
@Table(name = "t_entite_en")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "en_id", nullable = false))
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "en_type")
public abstract class Entity extends AbstractEntity<Long> {

    @Column(name = "en_nom", nullable = false)
    private String name;

    @Column(name = "en_adresse")
    private String address;

    @Column(name = "en_adresse_compl")
    private String additionalAddress;

    @Column(name = "en_code_postal")
    private String zipCode;

    @Column(name = "en_ville")
    private String city;

    @Column(name = "en_email")
    private String email;

    @Column(name = "en_telephone")
    private String phone;

    @Column(name = "en_site_web")
    private String website;

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
}
