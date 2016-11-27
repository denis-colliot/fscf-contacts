package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

import static fr.fscf.contacts.server.model.util.Entities.*;

/**
 * Habilitates a user on a feature for a given structure.
 */
@Entity
@Table(name = "t_habilitation_ha")
public class Habilitation extends AbstractEntity<HabilitationPK> {

    @EmbeddedId
    private HabilitationPK id = new HabilitationPK(); // Instantiation necessary.

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = USER_ID, nullable = false)
    private User user;

    @MapsId("featureId")
    @ManyToOne
    @JoinColumn(name = FEATURE_ID, nullable = false)
    private Feature feature;

    @MapsId("structureId")
    @ManyToOne
    @JoinColumn(name = STRUCTURE_ID, nullable = false)
    private Structure structure;

    public Habilitation() {
    }

    public Habilitation(User user, Feature feature, Structure structure) {
        setId(new HabilitationPK(user.getId(), feature.getId(), structure.getId()));
        setUser(user);
        setFeature(feature);
        setStructure(structure);
    }

    @Override
    protected Collection<String> toStringExcludedFields() {
        return Arrays.asList(Habilitation_.user.getName(),
                Habilitation_.feature.getName(),
                Habilitation_.structure.getName());
    }

    @Override
    public HabilitationPK getId() {
        return id;
    }

    @Override
    public void setId(HabilitationPK id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }
}
