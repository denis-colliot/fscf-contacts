package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractPK;
import fr.fscf.contacts.shared.dto.referential.StructureType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Function-StructureType composite primary key.
 */
@Embeddable
public class FunctionStructureTypePK extends AbstractPK {

    @Column(name = "structure_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private StructureType structureType;

    private Long functionId;

    public FunctionStructureTypePK() {
        // Empty constructor.
    }

    public FunctionStructureTypePK(final StructureType structureType, final Long functionId) {
        this.structureType = structureType;
        this.functionId = functionId;
    }

    @Override
    public boolean empty() {
        return structureType == null && functionId == null;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public void setStructureType(StructureType structureType) {
        this.structureType = structureType;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }
}
