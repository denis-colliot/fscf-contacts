package fr.fscf.contacts.shared.command;

import com.google.gwt.view.client.Range;
import fr.fscf.contacts.shared.command.base.AbstractTableCommand;
import fr.fscf.contacts.shared.dto.FunctionDTO;
import fr.fscf.contacts.shared.dto.StructureDTO;
import fr.fscf.contacts.shared.util.Sort;

import java.util.List;

/**
 * Gets structures list command.
 *
 * @author Denis
 */
public class GetStructuresCommand extends AbstractTableCommand<StructureDTO> {

    private Long functionId;

    public GetStructuresCommand() {
        // Serialization.
    }

    public GetStructuresCommand(Long functionId) {
        this(functionId, null, null);
    }

    public GetStructuresCommand(Long functionId, Range range, List<Sort> sorts) {
        super(range, sorts);
        this.functionId = functionId;
    }

    public Long getFunctionId() {
        return functionId;
    }
}
