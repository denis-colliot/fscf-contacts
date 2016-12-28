package fr.fscf.contacts.shared.dto.base;

import com.google.gwt.view.client.ProvidesKey;

import java.io.Serializable;
import java.util.Date;

/**
 * Abstract entity DTO (with id).
 *
 * @author Denis
 */
public abstract class AbstractEntityDTO<K extends Serializable> extends AbstractDTO {

    private K id;

    private Date creationDate;

    private String creationUser;

    private Date updateDate;

    private String updateUser;

    /**
     * Returns an implementation of {@link ProvidesKey} for an {@link AbstractEntityDTO}.
     *
     * @param <E> The entity DTO type.
     * @param <K> The entity type.
     * @return The {@link ProvidesKey} implementation.
     */
    public static <E extends AbstractEntityDTO<K>, K extends Serializable> ProvidesKey<E> keyProvider() {
        return entity -> entity != null ? entity.getId() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final AbstractEntityDTO<?> that = (AbstractEntityDTO<?>) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public final K getId() {
        return id;
    }

    public final void setId(K id) {
        this.id = id;
    }

    public final Date getCreationDate() {
        return creationDate;
    }

    public final void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public final String getCreationUser() {
        return creationUser;
    }

    public final void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public final Date getUpdateDate() {
        return updateDate;
    }

    public final void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public final String getUpdateUser() {
        return updateUser;
    }

    public final void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}
