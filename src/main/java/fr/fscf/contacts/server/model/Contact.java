package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static fr.fscf.contacts.server.model.util.Entities.*;

/**
 * Application contact.
 */
@Entity
@Table(name = "t_contact_co")
public class Contact extends AbstractEntity<Long> {

    // GenerationType.AUTO does not seem to work properly with H2 test database.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = HIBERNATE_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @Column(name = CONTACT_ID)
    private Long id;

    @Column(name = "co_nom", nullable = false)
    private String name;

    @Column(name = "co_prenom", nullable = false)
    private String firstName;

    @Column(name = "co_email")
    private String email;

    @Column(name = "co_telephone")
    private String phone;

    @Column(name = "co_adresse")
    private String address;

    @Column(name = "co_adresse_compl")
    private String additionalAddress;

    @Column(name = "co_code_postal")
    private String zipCode;

    @Column(name = "co_ville")
    private String city;

    @Column(name = "co_cedex")
    private String cedex;

    @Column(name = "co_email2")
    private String email2;

    @Column(name = "co_telephone2")
    private String phone2;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
    private List<Affectation> affectations;

    public Contact() {
    }

    public Contact(Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<String> toStringExcludedFields() {
        return Collections.singletonList(Contact_.affectations.getName());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Affectation> getAffectations() {
        return affectations;
    }

    public void setAffectations(List<Affectation> affectations) {
        this.affectations = affectations;
    }
}
