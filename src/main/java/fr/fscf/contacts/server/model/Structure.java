package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;
import fr.fscf.contacts.server.model.referential.StructureType;

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
@DiscriminatorColumn(name = STRUCTURE_TYPE)
public abstract class Structure extends AbstractEntity<Long> {

    // GenerationType.AUTO does not seem to work properly with H2 test database.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = HIBERNATE_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @Column(name = STRUCTURE_ID)
    private Long id;

    @Column(name = STRUCTURE_NAME, nullable = false)
    private String name;

    @Column(name = STRUCTURE_EMAIL)
    private String email;

    @Column(name = STRUCTURE_PHONE)
    private String phone;

    @Column(name = STRUCTURE_WEBSITE)
    private String website;

    @Column(name = STRUCTURE_ADDRESS)
    private String address;

    @Column(name = STRUCTURE_ADDITIONAL_ADDRESS)
    private String additionalAddress;

    @Column(name = STRUCTURE_ZIP_CODE)
    private String zipCode;

    @Column(name = STRUCTURE_CITY)
    private String city;

    @Column(name = STRUCTURE_CEDEX)
    private String cedex;

    @Column(name = STRUCTURE_EMAIL_2)
    private String email2;

    @Column(name = STRUCTURE_PHONE_2)
    private String phone2;

    @Column(name = STRUCTURE_COMMENTS)
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

    /**
     * Returns the given {@code structureType} corresponding entity class.
     *
     * @param structureType The structure type.
     * @return The corresponding structure class implementation.
     * @throws IllegalArgumentException If the structure type is invalid or unsupported.
     */
    public static Class<? extends Structure> getTypeClass(final String structureType) {
        if (structureType == null) {
            throw new IllegalArgumentException("Invalid structure type.");
        }
        switch (structureType.trim().toUpperCase()) {
            case StructureType.FEDERATION:
                return Federation.class;
            case StructureType.LIGUE_REGIONALE:
                return RegionalLeague.class;
            case StructureType.COMITE_DEPARTEMENTAL:
                return Comity.class;
            case StructureType.ASSOCIATION:
                return Association.class;
            default:
                throw new IllegalArgumentException("Unsupported structure type : " + structureType);
        }
    }

    public abstract String getType();

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
