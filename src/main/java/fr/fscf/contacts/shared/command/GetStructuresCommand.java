package fr.fscf.contacts.shared.command;

import com.google.gwt.view.client.Range;
import fr.fscf.contacts.shared.command.base.AbstractTableCommand;
import fr.fscf.contacts.shared.dto.StructureDTO;
import fr.fscf.contacts.shared.util.Sort;

/**
 * Gets structures list command.
 *
 * @author Denis
 */
public class GetStructuresCommand extends AbstractTableCommand<StructureDTO> {

    public GetStructuresCommand() {
        // Serialization.
    }

    public GetStructuresCommand(Range range, Sort sort) {
        super(range, sort);
    }

}
