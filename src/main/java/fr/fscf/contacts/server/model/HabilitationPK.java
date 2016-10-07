package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractPK;

import javax.persistence.Embeddable;

/**
 * Habilitation composite primary key.
 */
@Embeddable
public class HabilitationPK extends AbstractPK {

    private Long userId;

    private Long featureId;

    private Long structureId;

    public HabilitationPK() {
        // Empty constructor.
    }

    public HabilitationPK(final Long userId, final Long featureId, final Long structureId) {
        this.userId = userId;
        this.featureId = featureId;
        this.structureId = structureId;
    }

    @Override
    public boolean empty() {
        return userId == null && featureId == null && structureId == null;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }
}
