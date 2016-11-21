package fr.fscf.contacts.shared.command.base;

import com.google.gwt.view.client.Range;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dto.base.AbstractEntityDTO;
import fr.fscf.contacts.shared.util.Sort;

/**
 * <p>
 * Abstract command for table query with total count, sort and result list.
 * </p>
 *
 * @param <E> The list result entity type.
 * @author Denis
 */
public abstract class AbstractTableCommand<E extends AbstractEntityDTO<?>> extends AbstractCommand<ListResult<E>> {

    private Range range;
    private Sort sort;

    /**
     * Empty constructor necessary for RPC serialization.
     */
    protected AbstractTableCommand() {
        // Serialization.
    }

    protected AbstractTableCommand(Range range, Sort sort) {
        this.range = range;
        this.sort = sort;
    }

    public Range getRange() {
        return range;
    }

    public Sort getSort() {
        return sort;
    }
}