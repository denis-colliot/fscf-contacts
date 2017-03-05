package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;
import fr.fscf.contacts.shared.dto.referential.StructureType;

import javax.persistence.*;

import static fr.fscf.contacts.shared.util.Entities.FUNCTION_ID;

/**
 * Association between a function and a structure type.
 */
@Entity
@Table(name = "t_fonction_type_structure_fts")
public class FunctionStructureType extends AbstractEntity<FunctionStructureTypePK> {

    @EmbeddedId
    private FunctionStructureTypePK id = new FunctionStructureTypePK();

    @MapsId("functionId")
    @ManyToOne
    @JoinColumn(name = FUNCTION_ID, nullable = false)
    private Function function;

    public FunctionStructureType() {
    }

    public FunctionStructureType(StructureType structureType, Function function) {
        setId(new FunctionStructureTypePK(structureType, function.getId()));
        setFunction(function);
    }

    public StructureType getStructureType() {
        return id.getStructureType();
    }

    @Override
    public FunctionStructureTypePK getId() {
        return id;
    }

    @Override
    public void setId(FunctionStructureTypePK id) {
        this.id = id;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }
}
