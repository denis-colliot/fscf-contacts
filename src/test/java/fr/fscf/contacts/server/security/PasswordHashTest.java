package fr.fscf.contacts.server.security;

import fr.fscf.contacts.server.dao.AbstractDAOTest;
import org.junit.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordHashTest extends AbstractDAOTest {

    @Inject
    private Authenticator authenticator;

    @Test
    public void find() {
        System.out.println("HASH => " + authenticator.hashPassword("p4ssw0rd"));
        System.out.println("HASH => " + authenticator.hashPassword("p4ssw0rd"));

        final String hashed = authenticator.hashPassword("p4ssw0rd");
        assertThat(hashed).isNotNull();
        assertThat(hashed).hasSize(60);
    }

}
