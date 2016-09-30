package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static fr.fscf.contacts.server.model.util.Entities.*;

/**
 * Abstract structure.
 */
@javax.persistence.Entity
@Table(name = "t_structure_st")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "st_type")
public abstract class Structure extends AbstractEntity<Long> {

    // GenerationType.AUTO does not seem to work properly with H2 test database.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = HIBERNATE_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @Column(name = STRUCTURE_ID)
    private Long id;

    @Column(name = "st_nom", nullable = false)
    private String name;

    @Column(name = "st_email")
    private String email;

    @Column(name = "st_telephone")
    private String phone;

    @Column(name = "st_site_web")
    private String website;

    @Column(name = "st_adresse")
    private String address;

    @Column(name = "st_adresse_compl")
    private String additionalAddress;

    @Column(name = "st_code_postal")
    private String zipCode;

    @Column(name = "st_ville")
    private String city;

    @Column(name = "st_cedex")
    private String cedex;

    @Column(name = "st_email2")
    private String email2;

    @Column(name = "st_telephone2")
    private String phone2;

    @Column(name = "st_commentaire")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = DEPARTMENT_ID, nullable = true)
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "structure")
    private List<Habilitation> habilitations;

    protected Structure() {
    }

    protected Structure(Long id) {
        this.id = id;
    }

    @Override
    protected Collection<String> toStringExcludedFields() {
        return Arrays.asList(Structure_.department.getName(), Structure_.habilitations.getName());
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Habilitation> getHabilitations() {
        return habilitations;
    }

    public void setHabilitations(List<Habilitation> habilitations) {
        this.habilitations = habilitations;
    }
}
