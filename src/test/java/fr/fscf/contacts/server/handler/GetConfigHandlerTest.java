package fr.fscf.contacts.server.handler;

import fr.fscf.contacts.server.dao.AbstractDAOTest;
import fr.fscf.contacts.shared.command.result.MapResult;
import fr.fscf.contacts.shared.dispatch.CommandException;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class GetConfigHandlerTest extends AbstractDAOTest {

    @Inject
    private GetConfigHandler handler;

    @Test
    public void test() throws CommandException {
        final MapResult<String, String> result = handler.execute(null, null);

        assertThat(result).isNotNull();
        assertThat(result.get("app.name")).isEqualTo("Test application");
    }

}
