package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static fr.fscf.contacts.shared.util.Entities.*;

/**
 * Application user.<br/>
 * User email serves as authentication login.
 */
@javax.persistence.Entity
@Table(name = "t_utilisateur_ut")
public class User extends AbstractEntity<Long> {

    // GenerationType.AUTO does not seem to work properly with H2 test database.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = HIBERNATE_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @Column(name = USER_ID)
    private Long id;

    @Column(name = "ut_nom", nullable = false)
    private String name;

    @Column(name = "ut_prenom", nullable = false)
    private String firstName;

    @Column(name = "ut_email", nullable = false, unique = true)
    private String email;

    @Column(name = "ut_password", nullable = false)
    private String password;

    @Column(name = "ut_actif", nullable = true)
    private Boolean active;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Habilitation> habilitations;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<String> toStringExcludedFields() {
        return Arrays.asList(User_.habilitations.getName());
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Habilitation> getHabilitations() {
        return habilitations;
    }

    public void setHabilitations(List<Habilitation> habilitations) {
        this.habilitations = habilitations;
    }
}
