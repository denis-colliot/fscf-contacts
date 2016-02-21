package fr.fscf.contacts.server.model.base;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Abstract entity, parent class of all domain entities possessing a primary key.
 *
 * @param <K>
 *         Entity primary key type.
 * @author Denis
 */
@MappedSuperclass
public abstract class AbstractEntity<K extends Serializable> implements Entity<K> {

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "creation_user", nullable = true)
    private String creationUser;

    @Column(name = "update_date", nullable = true)
    private Date updateDate;

    @Column(name = "update_user", nullable = true)
    private String updateUser;

    /**
     * <p>
     * <b><em>Default {@code toString} method rendering all entity's properties.</em><br/>
     * See {@link #toStringExcludedFields()} to exclude specific properties.</b>
     * </p>
     * {@inheritDoc}
     *
     * @see #toStringExcludedFields()
     */
    @Override
    public final String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, toStringExcludedFields());
    }

    /**
     * Returns the properties names excluded from {@code toString()} process.
     *
     * @return The excluded properties names.
     */
    protected Collection<String> toStringExcludedFields() {
        return null;
    }

    /**
     * <p>
     * <b><em>Default {@code hashCode} method only relies on {@code id} property.</em></b>
     * </p>
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * <p>
     * <b><em>Default {@code equals} method only relies on {@code id} property.</em></b>
     * </p>
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        AbstractEntity<?> other = (AbstractEntity<?>) obj;
        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!getId().equals(other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String getCreationUser() {
        return creationUser;
    }

    @Override
    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    @Override
    public Date getUpdateDate() {
        return updateDate;
    }

    @Override
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}