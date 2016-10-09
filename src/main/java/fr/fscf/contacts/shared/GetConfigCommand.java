package fr.fscf.contacts.shared;

import fr.fscf.contacts.shared.command.base.AbstractCommand;
import fr.fscf.contacts.shared.command.result.MapResult;
import fr.fscf.contacts.shared.command.result.SecureNavigationResult;

import java.util.Map;

/**
 * Gets the application configuration command.
 *
 * @author Denis
 */
public class GetConfigCommand extends AbstractCommand<MapResult<String, String>> {

    public GetConfigCommand() {
        // Serialization.
    }

}
