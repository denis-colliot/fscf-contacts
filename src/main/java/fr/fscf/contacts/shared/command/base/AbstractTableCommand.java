package fr.fscf.contacts.shared.command.base;

import com.google.gwt.view.client.Range;
import fr.fscf.contacts.shared.command.result.ListResult;
import fr.fscf.contacts.shared.dto.base.AbstractEntityDTO;
import fr.fscf.contacts.shared.util.ClientUtils;
import fr.fscf.contacts.shared.util.Sort;

import java.util.List;

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
    private List<Sort> sorts;

    /**
     * Empty constructor necessary for RPC serialization.
     */
    protected AbstractTableCommand() {
        // Serialization.
    }

    protected AbstractTableCommand(Range range, List<Sort> sorts) {
        this.range = range;
        this.sorts = sorts;
    }

    public Range getRange() {
        return range;
    }

    public List<Sort> getSorts() {
        return sorts;
    }
}