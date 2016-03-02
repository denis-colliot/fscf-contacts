package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractPK;

import javax.persistence.Embeddable;

/**
 * Affectation composite primary key.
 */
@Embeddable
public class AffectationPK extends AbstractPK {

    private Long contactId;

    private Long structureId;

    private Long functionId;

    public AffectationPK() {
        // Empty constructor.
    }

    public AffectationPK(final Long contactId, final Long structureId, final Long functionId) {
        this.contactId = contactId;
        this.structureId = structureId;
        this.functionId = functionId;
    }

    @Override
    public boolean empty() {
        return contactId == null && structureId == null && functionId == null;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }
}
