package fr.fscf.contacts.server.model.base;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Abstract entity, parent class of all domain entities possessing a primary key.
 *
 * @param <K>
 *         Entity primary key type.
 * @author Denis
 */
@MappedSuperclass
public abstract class AbstractEntityAutoId<K extends Serializable> extends AbstractEntity<K> {

    // GenerationType.AUTO does not seem to work properly with H2 test database.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "hibernate_sequence", allocationSize = 1)
    @Column(name = "id")
    private K id;

    @Override
    public final K getId() {
        return id;
    }

    @Override
    public final void setId(K id) {
        this.id = id;
    }

}