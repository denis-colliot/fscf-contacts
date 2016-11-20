package fr.fscf.contacts.shared.dto.base;

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
