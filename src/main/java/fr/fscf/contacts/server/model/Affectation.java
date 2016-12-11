package fr.fscf.contacts.server.model;

import fr.fscf.contacts.server.model.base.AbstractEntity;
import fr.fscf.contacts.server.model.referential.AffectationStatus;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

import static fr.fscf.contacts.shared.util.Entities.*;

/**
 * Contact affectation.
 */
@Entity
@Table(name = "t_affectation_af")
public class Affectation extends AbstractEntity<AffectationPK> {

    @EmbeddedId
    private AffectationPK id = new AffectationPK(); // Instantiation necessary.

    @MapsId("contactId")
    @ManyToOne
    @JoinColumn(name = CONTACT_ID, nullable = false)
    private Contact contact;

    @MapsId("structureId")
    @ManyToOne
    @JoinColumn(name = STRUCTURE_ID, nullable = false)
    private Structure structure;

    @MapsId("functionId")
    @ManyToOne
    @JoinColumn(name = FUNCTION_ID, nullable = false)
    private Function function;

    @Column(name = "af_fonction_detaillee")
    private String detailedFunction;

    @Column(name = "af_statut", nullable = false)
    @Enumerated(EnumType.STRING)
    private AffectationStatus status;

    public Affectation() {
    }

    public Affectation(Contact contact, Structure structure, Function function, AffectationStatus status) {
        setId(new AffectationPK(contact.getId(), structure.getId(), function.getId()));
        setContact(contact);
        setStructure(structure);
        setFunction(function);
        setStatus(status);
    }

    @Override
    protected Collection<String> toStringExcludedFields() {
        return Arrays.asList(Affectation_.contact.getName(),
                Affectation_.structure.getName(),
                Affectation_.function.getName());
    }

    @Override
    public AffectationPK getId() {
        return id;
    }

    @Override
    public void setId(AffectationPK id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public String getDetailedFunction() {
        return detailedFunction;
    }

    public void setDetailedFunction(String detailedFunction) {
        this.detailedFunction = detailedFunction;
    }

    public AffectationStatus getStatus() {
        return status;
    }

    public void setStatus(AffectationStatus status) {
        this.status = status;
    }
}
