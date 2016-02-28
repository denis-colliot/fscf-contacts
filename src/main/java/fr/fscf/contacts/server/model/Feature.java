package fr.fscf.contacts.server.model;

import fr.fscf.contacts.client.navigation.Page;
import fr.fscf.contacts.server.model.base.AbstractEntity;
import fr.fscf.contacts.server.model.referential.GrantType;
import fr.fscf.contacts.shared.command.base.Command;
import fr.fscf.contacts.shared.servlet.Servlets;

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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = HABILITATION_TABLE,
            joinColumns = @JoinColumn(name = FEATURE_ID), inverseJoinColumns = @JoinColumn(name = USER_ID))
    private List<User> users;

    @Override
    protected Collection<String> toStringExcludedFields() {
        return Arrays.asList(Feature_.users.getName());
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
