package fr.fscf.contacts.server.model.base;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Collection;

/**
 * Abstract entity primary key.
 *
 * @author Denis
 */
public abstract class AbstractPK implements Serializable {

    public abstract boolean empty();

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

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public final boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}