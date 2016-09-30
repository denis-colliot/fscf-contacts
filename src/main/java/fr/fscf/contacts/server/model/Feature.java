package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;
import fr.fscf.contacts.server.model.referential.GrantType;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static fr.fscf.contacts.server.model.util.Entities.*;

/**
 * Application feature.
 */
@javax.persistence.Entity
@Table(name = "t_fonctionnalite_fe")
public class Feature extends AbstractEntity<Long> {

    // GenerationType.AUTO does not seem to work properly with H2 test database.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @SequenceGenerator(name = SEQUENCE_GENERATOR, sequenceName = HIBERNATE_SEQUENCE, allocationSize = ALLOCATION_SIZE)
    @Column(name = FEATURE_ID)
    private Long id;

    @Column(name = "fe_token", nullable = false, unique = true)
    private String token;

    @Column(name = "fe_type_acces", nullable = false)
    @Enumerated(EnumType.STRING)
    private GrantType grantType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "feature")
    private List<Habilitation> habilitations;

    @Override
    protected Collection<String> toStringExcludedFields() {
        return Arrays.asList(Feature_.habilitations.getName());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public GrantType getGrantType() {
        return grantType;
    }

    public void setGrantType(GrantType grantType) {
        this.grantType = grantType;
    }

    public List<Habilitation> getHabilitations() {
        return habilitations;
    }

    public void setHabilitations(List<Habilitation> habilitations) {
        this.habilitations = habilitations;
    }
}
